package com.ase.sanoapp.treatment.alarm;

import com.ase.sanoapp.treatment.BasePresenter;
import com.ase.sanoapp.treatment.BaseView;
import com.ase.sanoapp.treatment.source.History;
import com.ase.sanoapp.treatment.source.MedicineAlarm;


public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
