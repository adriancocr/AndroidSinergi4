package com.notas.cristhian.notas;

        import java.util.ArrayList;
        import android.content.Context;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.CheckBox;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import com.notas.cristhian.notas.R;

public class ListFileToStorageBase extends BaseAdapter
{

    static class ViewHolder
    {
        TextView tvTitulo;
        ImageButton imgPause;
        ImageButton imgPlay;
    }

    private static final String TAG = "CustomAdapter";
    private static int convertViewCounter = 0;

    private ArrayList<ListFilesToStorage> data;
    private LayoutInflater inflater = null;

    public ListFileToStorageBase(Context c, ArrayList<ListFilesToStorage> d)
    {
        Log.v(TAG, "Constructing CustomAdapter");
        this.data = d;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount()
    {
        Log.v(TAG, "in getCount()");
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        Log.v(TAG, "in getItem() for position " + position);
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        Log.v(TAG, "in getItemId() for position " + position);
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        Log.v(TAG, "in getViewTypeCount()");
        return 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        Log.v(TAG, "in getItemViewType() for position " + position);
        return 0;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder holder;

        Log.v(TAG, "in getView for position " + position + ", convertView is "
                + ((convertView == null) ? "null" : "being recycled"));

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.layout_listview, null);

            convertViewCounter++;
            Log.v(TAG, convertViewCounter + " convertViews have been created");

            holder = new ViewHolder();

            holder.tvTitulo = (TextView) convertView
                    .findViewById(R.id.tvTitulo);
            holder.imgPause = (ImageButton) convertView
                    .findViewById(R.id.imgPauseB);
            holder.imgPlay = (ImageButton) convertView.findViewById(R.id.imgPlayB);

            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();

        // Para poder hacer click en el checkbox
        ListFilesToStorage d = (ListFilesToStorage) getItem(position);
        holder.imgPlay.setTag(d);
        holder.imgPause.setTag(d);

        // Setting all values in listview
        holder.tvTitulo.setText(data.get(position).getTituloPista());
        holder.imgPlay.setImageResource(R.drawable.play);
        holder.imgPause.setImageResource(R.drawable.pause);

        return convertView;
    }

    public void setCheck(int position)
    {
        ListFilesToStorage d = data.get(position);
        d.setMarcado(!d.getMarcado());
        notifyDataSetChanged();
    }

    public void checkAll(boolean state)
    {
        for (int i = 0; i < data.size(); i++)
            data.get(i).setMarcado(state);
    }

    public void cancelSelectedPost()
    {

        int i = 0;
        while (i < getCount())
        {
            if (data.get(i).getMarcado())
            {
                data.remove(data.indexOf(data.get(i)));
            } else
                i++;
        }
        notifyDataSetChanged();
    }

    public boolean haveSomethingSelected()
    {
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).getMarcado())
                return true;
        return false;
    }

    /**
     * Este método es para poder seleccionar una fila directamente con el
     * checkbox en lugar de tener que pulsar en la liste en sí
     */
    private OnClickListener marcadoListener = new OnClickListener()
    {

        @Override
        public void onClick(View v)
        {
            ListFilesToStorage d = (ListFilesToStorage) v.getTag();
            d.setMarcado(!d.getMarcado());
            //aqui se dispararian los metodos reproducir o eliminar
        }
    };
}