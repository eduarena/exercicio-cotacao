package br.com.example.fiap.mob8.exerciciocotacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.example.fiap.mob8.exerciciocotacao.R;
import br.com.example.fiap.mob8.exerciciocotacao.model.Cotacao;

/**
 * Created by eduardo on 8/22/15.
 */
public class CotacaoAdapter extends BaseAdapter {

    private Context context;
    private List<Cotacao> cotacoes;
    private LayoutInflater inflater = null;

    public CotacaoAdapter(Context context, List<Cotacao>cotacoes){
        this.context = context;
        this.cotacoes = cotacoes;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return cotacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return cotacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cotacoes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HolderView holderView = new HolderView();

        if(convertView != null) {

            View row;
            row = inflater.inflate(R.layout.row_cotacao_item, parent, false);

            holderView.ivImageFlags = (ImageView) row.findViewById(R.id.ivImageFlags);
            holderView.tvCountry = (TextView) row.findViewById(R.id.tvCountry);
            holderView.tvCotacao = (TextView) row.findViewById(R.id.tvCotacao);
            holderView.tvVariacao = (TextView) row.findViewById(R.id.tvVariacao);
            holderView.tvValueCotacao = (TextView) row.findViewById(R.id.tvValueCotacao);
            holderView.tvValueVariacao = (TextView) row.findViewById(R.id.tvValueVariacao);

        }

        return convertView;
    }


    public class HolderView {
        ImageView ivImageFlags;
        TextView tvCountry;
        TextView tvCotacao;
        TextView tvVariacao;
        TextView tvValueCotacao;
        TextView tvValueVariacao;
    }

}
