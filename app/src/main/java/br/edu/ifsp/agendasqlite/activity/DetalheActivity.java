package br.edu.ifsp.agendasqlite.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import br.edu.ifsp.agendasqlite.R;
import br.edu.ifsp.agendasqlite.data.ContatoDAO;
import br.edu.ifsp.agendasqlite.model.Contato;
import br.edu.ifsp.agendasqlite.utils.DatePickeFragment;

public class DetalheActivity extends AppCompatActivity {

    public static final String CAMPO_OBRIGATORIO_NAO_INFORMADO = "Campo Obrigatório não informado";
    private EditText edtNome, edtFone, edtFoneAlternativo, edtEmail, edtData;

    Contato c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        if (getIntent().hasExtra("contato"))
        {
            this.c = (Contato)getIntent().getSerializableExtra("contato");

            EditText nome = findViewById(R.id.editTextNome);
            nome.setText(c.getNome());

            EditText fone = findViewById(R.id.editTextFone);
            fone.setText(c.getFone());

            EditText foneAlternativo = findViewById(R.id.editTextFoneAlternativo);
            foneAlternativo.setText(c.getFoneAlternativo());

            EditText email = findViewById(R.id.editTextEmail);
            email.setText(c.getEmail());

            TextView dataAniversario = findViewById(R.id.editTextDate);
            dataAniversario.setText(c.getDataAniversario());

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhe, menu);
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
        if (id == R.id.action_alterarContato) {
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
                String data = edtData.getText().toString();
                String email = edtEmail.getText().toString();

                c.setNome(nome);
                c.setFone(fone);
                c.setFoneAlternativo(foneAlternativo);
                c.setEmail(email);
                c.setDataAniversario(data);

                dao.alterarContato(c);
                Log.d("ID: ", Integer.toString(c.getId()));
                Log.d("NOME: ", c.getNome());

                MainActivity.adapter.atualizaContatoAdapter(c);

                Toast.makeText(getApplicationContext(), "Contato alterado", Toast.LENGTH_LONG).show();

                finish();
            }
        }

        if (id ==R.id.action_excluirContato) {
            ContatoDAO dao = new ContatoDAO(this);
            dao.excluirContato(c);
            MainActivity.adapter.apagaContatoAdapter(c);

            Toast.makeText(getApplicationContext(),"Contato excluído",Toast.LENGTH_LONG).show();
            finish();

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
