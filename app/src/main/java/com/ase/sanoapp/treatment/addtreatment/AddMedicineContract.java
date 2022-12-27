package com.ase.sanoapp.treatment.addtreatment;

import com.ase.sanoapp.treatment.BasePresenter;
import com.ase.sanoapp.treatment.BaseView;
import com.ase.sanoapp.treatment.source.MedicineAlarm;
import com.ase.sanoapp.treatment.source.Pills;

import java.util.List;


public interface AddMedicineContract {

    interface View extends BaseView<Presenter> {

        void showEmptyMedicineError();

        void showMedicineList();

        boolean isActive();

    }

    interface  Presenter extends BasePresenter {


        void saveMedicine(MedicineAlarm alarm, Pills pills);


        boolean isDataMissing();

        boolean isMedicineExits(String pillName);

        long addPills(Pills pills);

        Pills getPillsByName(String pillName);

        List<MedicineAlarm> getMedicineByPillName(String pillName);

        List<Long> tempIds();

        void deleteMedicineAlarm(long alarmId);

    }
}
