package br.com.dlweb.maternidade.mae;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.dlweb.maternidade.R;

public class AdicionarFragment extends Fragment {

    private EditText editTextNome;
    private EditText editTextCep;
    private EditText editTextLogradouro;
    private EditText editTextNumero;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextFixo;
    private EditText editTextCelular;
    private EditText editTextComercial;
    private EditText editTextDataNascimento;

    private FirebaseFirestore db;

    public AdicionarFragment() {
    }

    private void adicionar () {
        if (editTextNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (editTextCep.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o cep!", Toast.LENGTH_LONG).show();
        } else if (editTextLogradouro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o logradouro!", Toast.LENGTH_LONG).show();
        } else if (editTextNumero.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o numero!", Toast.LENGTH_LONG).show();
        } else if (editTextBairro.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o bairro!", Toast.LENGTH_LONG).show();
        } else if (editTextCidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o cidade!", Toast.LENGTH_LONG).show();
        } else if (editTextFixo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o fixo!", Toast.LENGTH_LONG).show();
        } else if (editTextCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o celular!", Toast.LENGTH_LONG).show();
        } else if (editTextComercial.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o comercial!", Toast.LENGTH_LONG).show();
        } else if (editTextDataNascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nascimento!", Toast.LENGTH_LONG).show();
        } else {
            Mae m = new Mae();
            m.setNome(editTextNome.getText().toString());
            m.setCep(editTextCep.getText().toString());
            m.setLogradouro(editTextLogradouro.getText().toString());
            m.setNumero(Integer.parseInt(editTextNumero.getText().toString()));
            m.setBairro(editTextBairro.getText().toString());
            m.setCidade(editTextCidade.getText().toString());
            m.setFixo(editTextFixo.getText().toString());
            m.setCelular(editTextCelular.getText().toString());
            m.setComercial(editTextComercial.getText().toString());
            m.setDataNascimento(editTextDataNascimento.getText().toString());

            CollectionReference collectionMae = db.collection("Maes");
            collectionMae.add(m).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getActivity(), "Mãe salva!", Toast.LENGTH_LONG).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMae, new ListarFragment()).commit();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Erro ao salvar a mãe!", Toast.LENGTH_LONG).show();
                    Log.d("AdicionarMae", "mensagem de erro: ", e);
                }
            });
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.mae_fragment_adicionar, container, false);
        Button btnSalvar = v.findViewById(R.id.buttonAdicionar);

        editTextNome = v.findViewById(R.id.editTextNome);
        editTextCep = v.findViewById(R.id.editTextCep);
        editTextLogradouro = v.findViewById(R.id.editTextLogradouro);
        editTextNumero = v.findViewById(R.id.editTextNumero);
        editTextBairro = v.findViewById(R.id.editTextBairro);
        editTextCidade = v.findViewById(R.id.editTextCidade);
        editTextFixo = v.findViewById(R.id.editTextFixo);
        editTextCelular = v.findViewById(R.id.editTextCelular);
        editTextComercial = v.findViewById(R.id.editTextComercial);
        editTextDataNascimento = v.findViewById(R.id.editTextDataNascimento);

        db = FirebaseFirestore.getInstance();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMae, new	ListarFragment()).commit();
            }
        });

        return v;
    }
}