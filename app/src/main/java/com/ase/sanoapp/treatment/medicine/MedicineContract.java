package com.ase.sanoapp.treatment.medicine;

import android.content.Context;

import com.ase.sanoapp.treatment.BasePresenter;
import com.ase.sanoapp.treatment.BaseView;
import com.ase.sanoapp.treatment.source.MedicineAlarm;

import java.util.List;



public interface MedicineContract {

    interface View extends BaseView<Presenter> {

        void showLoadingIndicator(boolean active);

        void showMedicineList(List<MedicineAlarm> medicineAlarmList);

        void showAddMedicine();

        void showMedicineDetails(long medId, String medName);

        void showLoadingMedicineError();

        void showNoMedicine();

        void showSuccessfullySavedMessage();

        void  showMedicineDeletedSuccessfully();

        boolean isActive();


    }

    interface Presenter extends BasePresenter {

        void onStart(int day);

        void reload(int day);

        void result(int requestCode, int resultCode);

        void loadMedicinesByDay(int day, boolean showIndicator);

        void deleteMedicineAlarm(MedicineAlarm medicineAlarm, Context context);

        void addNewMedicine();

    }
}
