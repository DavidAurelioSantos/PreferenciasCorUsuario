package com.preferenciascorusuario;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button botaoSalvar;
    private RelativeLayout layout;

    private static final String ARQUIVO_PREFERENCIA = "ArqPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroupId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);
        layout = (RelativeLayout) findViewById(R.id.layoutId);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Aqui estou pegando o ID do Radio Button selecionado e gravando na variável idRadioButtonEscolhido
                int idRadioButtonEscolhido = radioGroup.getCheckedRadioButtonId();
                if (idRadioButtonEscolhido > 0 ) {

                //Fazendo o cast pegando o id do radio button selecionado
                radioButtonSelecionado = (RadioButton) findViewById(idRadioButtonEscolhido);

                    SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
                    // Associando a variável editor ao arquivo para edição
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String corEscolhida = radioButtonSelecionado.getText().toString();

                    // Primeiro item do parametro, colocanco a chave da cor escolhida. Segundo item do parametro, recuperando o que foi escolhido.
                    editor.putString("CorEscolhida", corEscolhida);
                    editor.commit();

                    setBackground(corEscolhida);
                }
            }
        });

        //Recuperar a cor que foi salva
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);
        if (sharedPreferences.contains("CorEscolhida")) {
            //Caso não encontre a cor escolhida, coloque como padrão o Laranja
            String corRecuperada = sharedPreferences.getString("CorEscolhida", "Laranja");
            setBackground(corRecuperada);
        }

    }

    private void setBackground(String cor){
        if ( cor.equals("Azul")) {
            layout.setBackgroundColor(Color.parseColor("#495b7c"));
        }else if (cor.equals("Laranja")) {
            layout.setBackgroundColor(Color.parseColor("#ffce26"));
        }else if (cor.equals("Verde")) {
            layout.setBackgroundColor(Color.parseColor("#009670"));
        }
    }
}
