package cbu.httf.adisyonprogram.Fragment.Category;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import cbu.httf.adisyonprogram.Activity.MenuTransactActivity;
import cbu.httf.adisyonprogram.Network.Service;
import cbu.httf.adisyonprogram.R;
import cbu.httf.adisyonprogram.data.model.CategoryModel;
import cbu.httf.adisyonprogram.data.model.ResultModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryUpdateFragment extends BottomSheetDialogFragment {

    private ImageView imgClose;
    private Button putCategory;

    private EditText editTextCategoryName;
    private EditText editTextCategoryID;
    private int categoryId;
    private String categoryName;

    private String token;

    public CategoryUpdateFragment(String token,int categoryId) {
        this.token=token;
        this.categoryId=categoryId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_category,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        editTextCategoryID=(EditText)view.findViewById(R.id.editTextUpdateCategoryId);
        editTextCategoryName=(EditText)view.findViewById(R.id.editTextAddCategoryName);

        if(categoryId!=0)
            editTextCategoryID.setText(String.valueOf(categoryId));

        imgClose = view.findViewById(R.id.update_category_imgClose);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        putCategory = (Button)view.findViewById(R.id.putCategory);

        putCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editTextCategoryName.getText().toString())||
                        !TextUtils.isEmpty(editTextCategoryID.getText().toString())){

                    categoryId= Integer.parseInt(editTextCategoryID.getText().toString());
                    categoryName= editTextCategoryName.getText().toString();

                    CategoryModel categoryModel = new CategoryModel(categoryId,categoryName);

                    Call<ResultModel> categoryModelCall = Service.getServiceApi().putCategory(token,categoryModel);
                    categoryModelCall.enqueue(new Callback<ResultModel>() {
                        @Override
                        public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Request Successful." , Toast.LENGTH_LONG).show();
                                ((MenuTransactActivity)getActivity()).recreate();
                                dismiss();

                            } else {
                                Toast.makeText(getContext(), "Request failed. ("+response.code()+")" , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultModel> call, Throwable t) {
                            Toast.makeText(getContext(), "Failure: "+t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(getContext(), "Boş alanları doldurunuz.", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
