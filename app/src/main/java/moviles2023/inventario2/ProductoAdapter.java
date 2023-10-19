package moviles2023.inventario2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import moviles2023.inventario2.data.Producto;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    public ProductoAdapter(@NonNull Context context, ArrayList<Producto> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtener inflater.

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_user_item,parent,false);
        }

        TextView iditem = (TextView) convertView.findViewById(R.id.itemProductID);
        TextView quantityitem = (TextView) convertView.findViewById(R.id.itemProductQuantity) ;
        TextView quantitysitem = (TextView) convertView.findViewById(R.id.itemProductQuantityS);

        Producto productoActual = getItem(position);

        iditem.setText(String.valueOf(productoActual.getId()));
        quantityitem.setText(String.valueOf(productoActual.getQuantity()));
        quantitysitem.setText(String.valueOf(productoActual.getQuantitys()));

        return convertView;
    }


}
