package moviles2023.inventario2;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import moviles2023.inventario2.data.Producto;

public class ProductoAdapterRecycler extends RecyclerView.Adapter<ProductoAdapterRecycler.ViewHolder> {

    private Cursor cursorListaProductos;

    private OnItemClickListener listenerClick;

    public  ProductoAdapterRecycler(OnItemClickListener listenerClick) {
        this.listenerClick = listenerClick;
    }

    interface OnItemClickListener{
        public void onClick(ViewHolder view, Producto productoactualizado);
    }
    @NonNull
    @Override
    public ProductoAdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapterRecycler.ViewHolder holder, int position) {
        cursorListaProductos.moveToPosition(position);
        Producto productoSeleccionado = new Producto(cursorListaProductos);
        holder.idProducto.setText(String.valueOf(productoSeleccionado.getId()));
        holder.quantityProducto.setText(String.valueOf(productoSeleccionado.getQuantity()));
        holder.quantitysProducto.setText(String.valueOf(productoSeleccionado.getQuantitys()));

    }

    @Override
    public int getItemCount() {
        if (cursorListaProductos != null)
            return cursorListaProductos.getCount();
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor){
        if(nuevoCursor!=null){
            cursorListaProductos= nuevoCursor;
            notifyDataSetChanged();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView idProducto;
        TextView quantityProducto;
        TextView quantitysProducto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idProducto = (TextView) itemView.findViewById( R.id.itemProductID );
            quantityProducto= (TextView) itemView.findViewById( R.id.itemProductQuantity );
            quantitysProducto= (TextView) itemView.findViewById( R.id.itemProductQuantityS );
            itemView.setOnClickListener( this );

        }

        @Override
        public void onClick(View view) {
            Producto ProductoClickeado = obtenerProducto(getAdapterPosition());
            ProductoClickeado.setQuantity(ProductoClickeado.getQuantity() - ProductoClickeado.getQuantitys());
            listenerClick.onClick(this, ProductoClickeado);
        }
        }

        private Producto obtenerProducto(int posicion){
            if (cursorListaProductos!=null){
                cursorListaProductos.moveToPosition( posicion );
                Producto nuevoProducto = new Producto( cursorListaProductos );
                return nuevoProducto;
            }
            return null;
    }
}
