package com.example.pegados.appdesafios.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.Desafio;

public class RespostaFragment extends Fragment implements View.OnClickListener{

    private VerificaResposta listener;
    //private EditText edt_resposta;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof VerificaResposta)){
            throw new RuntimeException("Deve ser um VerificaResposta");
        }

        this.listener = (VerificaResposta) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_resposta, container, false);

        view.findViewById(R.id.edit_resposta);
        view.findViewById(R.id.btn_responder_desafio).setOnClickListener(this);
        view.findViewById(R.id.btn_cancelar_desafio).setOnClickListener(this);

        //edt_resposta = view.findViewById(R.id.edit_resposta);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_responder_desafio){
            listener.verificarResposta();
        }
        /*Desafio desafio = (Desafio) getActivity().getIntent().getSerializableExtra("desafio");
        String resposta = edt_resposta.getText().toString();
        Toast.makeText(getActivity(), desafio.getTitulo(), Toast.LENGTH_SHORT).show();*/
        if (v.getId() == R.id.btn_cancelar_desafio){
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        }
    }

    public interface VerificaResposta{
        void verificarResposta ();
    }
}
