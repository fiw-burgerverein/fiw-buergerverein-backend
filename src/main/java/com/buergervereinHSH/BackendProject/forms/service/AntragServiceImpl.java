package com.buergervereinHSH.BackendProject.forms.service;

import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.AntragDto;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.SachkostenDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.FormDao;
import com.buergervereinHSH.BackendProject.forms.model.Formular;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class AntragServiceImpl implements AntragService {

    @Autowired
    private SachkostenDao sachkostenDao;
    @Autowired
    private FormDao formDao;


    @Override
    public ApiResponse saveForm(AntragDto antragDto) {

        Formular currformular = new Formular();

        BeanUtils.copyProperties(antragDto, currformular, "sachList", "sachkostenSum");

        ArrayList<Sachkosten> currentSList = antragDto.getSachList();

        int gesSachkosten = 0;

        for(int i=0; i<currentSList.size(); i++){
            Sachkosten singleSach = currentSList.get(i);
          //  singleSach.setFormId(currformular.getFormId());
            gesSachkosten+=singleSach.getKosten();
            sachkostenDao.save(singleSach);
        }

        currformular.setSachkostenSum(gesSachkosten);
        formDao.save(currformular);

        /*for(Sachkosten sachkosten: currentSList){
            Sachkosten singleSach = new Sachkosten();

            //antragsId setzen für sachkosten; antrag.getUserId()
            sachkostenDao.save(sachkosten);
        }*/

        return new ApiResponse(200, "Anrag erfolgreich gestellt", null);
    }

}

/*import com.buergervereinHSH.BackendProject.auth.web.ApiResponse;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.AntragDao;
import com.buergervereinHSH.BackendProject.forms.dataAccessObject.SachkostenDao;
import com.buergervereinHSH.BackendProject.forms.dataTransferObject.request.AntragDto;
import com.buergervereinHSH.BackendProject.forms.model.Antrag;
import com.buergervereinHSH.BackendProject.forms.model.Sachkosten;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

public class AntragServiceImpl implements AntragService {

    @Autowired
    private AntragDao antragDao;

    @Autowired
    private ArrayList<Sachkosten> sachkosten;

    @Autowired
    private SachkostenDao sachkostenDao;

    @Override
    public ApiResponse saveForm(AntragDto antragDto) {

        Antrag antrag = new Antrag();
        BeanUtils.copyProperties(antragDto, antrag); //was ist mit Listen?

        for(Sachkosten sachkosten: sachkosten){
            //antragsId setzen für sachkosten; antrag.getUserId()
            sachkostenDao.save(sachkosten);
        }

        return new ApiResponse(200, "Antrag erfolgreich gespeichert", null);
    }

    @Override
    public float SachkostenGesamt(AntragDto antragDto) {

        float gesamtSachKosten;
        //ArrayList<Sachkosten> table = antragDao.findByFormId(table.getFormId());

        for(int i=0; i<antragDto.getSachkostenList().size(); i++)
        {
        }

        return 0;
    }*/

