package com.ase.sanoapp.treatment.mock;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ase.sanoapp.treatment.source.MedicineRepository;
import com.ase.sanoapp.treatment.source.local.MedicinesLocalDataSource;


public class Injection {

    public static MedicineRepository provideMedicineRepository(@NonNull Context context) {
        return MedicineRepository.getInstance(MedicinesLocalDataSource.getInstance(context));
    }
}
