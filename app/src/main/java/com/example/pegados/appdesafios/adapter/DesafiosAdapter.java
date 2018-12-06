package com.example.pegados.appdesafios.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pegados.appdesafios.R;
import com.example.pegados.appdesafios.data.Desafio;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DesafiosAdapter extends BaseAdapter {

	private static final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	private Context context;
	private List<Desafio> desafios = new ArrayList<>();

	public DesafiosAdapter(Context context) {

		this.context = context;
	}

	@Override
	public int getCount() {
		return desafios.size();
	}

	@Override
	public Desafio getItem(int position) {
		return desafios.get(position);
	}

	@Override
	public long getItemId(int position) {
		return desafios.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = inflater.inflate(R.layout.adapter_list_desafios, parent, false);

			holder = new ViewHolder();
			holder.txtTitulo = view.findViewById(R.id.txt_titulo);
			holder.txtValorTentativa = view.findViewById(R.id.txt_valor_tentativa);
			holder.txtValorPremio = view.findViewById(R.id.txt_valor_premio);

			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}

		Desafio desafio = desafios.get(position);

		//holder.txtNome.setText(desafio.getNome());
		//holder.txtValor.setText(nf.format(desafio.getValor()));

		holder.txtTitulo.setText(desafio.getTitulo());
		holder.txtValorTentativa.setText(String.valueOf(desafio.getValorTentativa()));
		holder.txtValorPremio.setText(String.valueOf(desafio.getValorPremio()));

		return view;
	}

	public void setItems(List<Desafio> desafios) {
		this.desafios = desafios;
		notifyDataSetChanged();
	}

	private static class ViewHolder {
		public TextView txtTitulo;
		public TextView txtValorTentativa;
		public TextView txtValorPremio;
	}
}
