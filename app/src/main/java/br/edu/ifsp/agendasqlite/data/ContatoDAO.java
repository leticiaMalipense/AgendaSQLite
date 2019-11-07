package br.edu.ifsp.agendasqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.agendasqlite.model.Contato;

public class ContatoDAO {

    SQLiteDatabase database;
    SQLiteHelper dbHelper;

    public ContatoDAO(Context context)
    {
        this.dbHelper = new SQLiteHelper(context);
    }

    public List<Contato> listaContatos()
    {
        database = dbHelper.getReadableDatabase();

        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        cursor = database.query(SQLiteHelper.TABLE_NAME,
                                null,
                                null,
                                null,
                                null,
                                 null,
                                    SQLiteHelper.KEY_NOME);

        while (cursor.moveToNext())
        {
           Contato c = new Contato();
           c.setId(cursor.getInt(0));
           c.setNome(cursor.getString(1));
           c.setFone(cursor.getString(2));
           c.setEmail(cursor.getString(3));

           contatos.add(c);
        }

        cursor.close();
        database.close();

        return contatos;
    }


    public long incluirContato(Contato c)
    {

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NOME, c.getNome());
        values.put(SQLiteHelper.KEY_FONE, c.getFone());
        values.put(SQLiteHelper.KEY_EMAIL, c.getEmail());

        long id = database.insert(SQLiteHelper.TABLE_NAME, null, values);

        database.close();
        return id;

    }

    public void alterarContato(Contato c)
    {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NOME, c.getNome());
        values.put(SQLiteHelper.KEY_FONE, c.getFone());
        values.put(SQLiteHelper.KEY_EMAIL, c.getEmail());

        database.update(SQLiteHelper.TABLE_NAME, values,
                     SQLiteHelper.KEY_ID +"=" +c.getId(),null);

        database.close();
    }

    public void excluirContato (Contato c)
    {
        database = dbHelper.getWritableDatabase();

        database.delete(SQLiteHelper.TABLE_NAME,
                        SQLiteHelper.KEY_ID +"="+ c.getId(),null);

        database.close();

    }

}
