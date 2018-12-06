package com.example.pegados.appdesafios.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class RespostaDialog extends DialogFragment implements DialogInterface.OnClickListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Você tem certeza da sua resposta?");
		builder.setPositiveButton("Sim", this);
		builder.setNegativeButton("Não", this);
		return builder.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if (which == DialogInterface.BUTTON_POSITIVE) {
		}
		else {


		}
	}

}
