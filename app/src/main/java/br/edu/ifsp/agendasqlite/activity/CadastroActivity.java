package br.edu.ifsp.agendasqlite.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.agendasqlite.R;
import br.edu.ifsp.agendasqlite.data.ContatoDAO;
import br.edu.ifsp.agendasqlite.model.Contato;
import br.edu.ifsp.agendasqlite.utils.DatePickeFragment;

public class CadastroActivity extends AppCompatActivity {

    public static final String CAMPO_OBRIGATORIO_NAO_INFORMADO = "Campo Obrigatório não informado";
    private EditText edtNome, edtFone, edtFoneAlternativo, edtEmail, edtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DatePickeFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_salvarContato) {
            ContatoDAO dao = new ContatoDAO(this);

            edtNome = findViewById(R.id.editTextNome);
            edtFone = findViewById(R.id.editTextFone);
            edtFoneAlternativo = findViewById(R.id.editTextFoneAlternativo);
            edtEmail = findViewById(R.id.editTextEmail);
            edtData = findViewById(R.id.editTextDate);

            if(validaCamposObrigatorio()) {

                String nome = edtNome.getText().toString();
                String fone = edtFone.getText().toString();
                String foneAlternativo = edtFoneAlternativo.getText().toString();
                String data =  edtData.getText().toString();
                String email = edtEmail.getText().toString();

                Contato c = new Contato(nome, fone, foneAlternativo, data, email);

                int idContato = (int) dao.incluirContato(c);
                c.setId(idContato);

                MainActivity.adapter.adicionaContatoAdapter(c);

                Toast.makeText(getApplicationContext(), "Contato inserido", Toast.LENGTH_LONG).show();

                finish();
            }


        }

        return super.onOptionsItemSelected(item);
    }


    public boolean validaCamposObrigatorio(){
        boolean validated = true;
        if(edtNome.getText() == null || edtNome.getText().length() == 0){
            edtNome.setError(CAMPO_OBRIGATORIO_NAO_INFORMADO);
            validated = false;
        }

        if(edtFone.getText() == null || edtFone.getText().length() == 0){
            edtFone.setError(CAMPO_OBRIGATORIO_NAO_INFORMADO);
            validated = false;
        }

        if(edtFoneAlternativo.getText() == null || edtFoneAlternativo.getText().length() == 0){
            edtFoneAlternativo.setError(CAMPO_OBRIGATORIO_NAO_INFORMADO);
            validated = false;
        }

        if(edtEmail.getText() == null || edtEmail.getText().length() == 0){
            edtEmail.setError(CAMPO_OBRIGATORIO_NAO_INFORMADO);
            validated = false;
        }

        if(edtData.getText() == null || edtData.getText().length() == 0){
            edtData.setError(CAMPO_OBRIGATORIO_NAO_INFORMADO);
            validated = false;
        }

        return validated;
    }


}
