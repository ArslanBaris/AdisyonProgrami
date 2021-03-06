package cbu.httf.adisyonprogram.Adapters;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cbu.httf.adisyonprogram.R;
import cbu.httf.adisyonprogram.data.model.Item;
import cbu.httf.adisyonprogram.data.model.OrderList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CardHolder>{
    private List<OrderList> Item_List;
    public List<OrderList> Test;
    public int Item_ID,Table_ID;
    public String TestName,TestPiece;
    //Table_Adapter olacak adi.
    public ItemAdapter(List<OrderList> item_List) {
        this.Item_List = item_List;
    }
    @NonNull
    @Override
    public ItemAdapter.CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView;
        cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ItemAdapter.CardHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.CardHolder holder, int position) {
        holder.ItemName.setText(Item_List.get(position).getItem_Name());
        holder.ItemPiece.setText(Item_List.get(position).getItem_Piece());
        holder.ItemPiece.setOnClickListener(View -> {
            Test = new ArrayList<>();
            Test.add(new OrderList(Item_List.get(position).getItem_Name(),Item_List.get(position).getItem_Piece(),Item_List.get(position).getItem_ID(),Item_List.get(position).getTable_ID()));
            TestName = Item_List.get(position).getItem_Name();
            TestPiece = Item_List.get(position).getItem_Piece();
            Item_ID = Item_List.get(position).getItem_ID();
            Table_ID = Item_List.get(position).getTable_ID();
            getItem_List();
        });
    }
    public List<OrderList> getItem_List(){
        List<OrderList> test = new ArrayList<>();
        test.add(new OrderList(TestName,TestPiece,Item_ID,Table_ID));
        return test;
    }
    @Override
    public int getItemCount() {
        return Item_List.size();
    }

    public class CardHolder extends RecyclerView.ViewHolder {
        public TextView tv_Name, tv_Piece, ItemPiece, ItemName;
        public Button btn_pls, btn_min;
        public CardHolder(@NonNull View itemView) {
            super(itemView);
            tv_Name = itemView.findViewById(R.id.tv_Name);
            tv_Piece = itemView.findViewById(R.id.tv_Piece);
            btn_min = itemView.findViewById(R.id.btn_min);
            btn_pls = itemView.findViewById(R.id.btn_pls);
            ItemName = itemView.findViewById(R.id.ItemName);
            ItemPiece = itemView.findViewById(R.id.ItemPiece);
        }
    }
}
