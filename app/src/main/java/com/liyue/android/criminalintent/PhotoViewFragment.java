package com.liyue.android.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import static com.liyue.android.criminalintent.database.RecordDbSchema.RecordTable.Cols.UUID;

/**
 * Created by luli01 on 2016/10/19.
 */

public class PhotoViewFragment extends DialogFragment {
    private static final String ARG_UUID = "uuid";
    private ImageView mPhotoView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final UUID uuid = (UUID)getArguments().getSerializable(ARG_UUID);
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_photo, null);

        mPhotoView = (ImageView)v.findViewById(R.id.dialog_photo_image_view);
        File photoFile = RecordLab.get(getActivity()).getPhotoFile(RecordLab.get(getActivity()).getRecord(uuid));;
        Bitmap bitmap = PictureUtils.getScaledBitmap(photoFile.getPath(), getActivity());
        mPhotoView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    public static PhotoViewFragment newInstance(UUID uuid){
        Bundle args = new Bundle();
        args.putSerializable(ARG_UUID, uuid);

        PhotoViewFragment fragment = new PhotoViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
