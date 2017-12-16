package com.heavenking.indro.xxxx;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener{

    private EditText editusername,editemail,password;
    private Button buttonregister;
    private ProgressDialog progressDialog;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        editusername = (EditText) view.findViewById(R.id.editTextUsername);
        editemail = (EditText) view.findViewById(R.id.editTextEmail);
        password = (EditText) view.findViewById(R.id.editTextPassword);

        buttonregister = (Button)view.findViewById(R.id.buttonRegister);

        progressDialog = new ProgressDialog(getActivity());

        buttonregister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == buttonregister)
            registerUser();

    }

    private void registerUser() {
        final String email = editemail.getText().toString().trim();
        final String username = editusername.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        progressDialog.setMessage("Registering User......");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject != null ) {

                                String result = jsonObject.getString("message");
                                if(result.equals("It seems you are already registered, please choose a different email and username")){
                                    Toast.makeText(getActivity().getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                                }else{
                                    //have fun
                                }
                            }
                            Toast.makeText(getActivity().getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                params.put("username",username);
                params.put("email",email);
                params.put("password",pass);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(stringRequest);
    }

}
