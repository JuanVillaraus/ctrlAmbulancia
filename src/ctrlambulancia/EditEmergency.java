/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class EditEmergency extends JPanel implements ActionListener {

    int idEmergency = 0;
    tabData tab;
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat timeFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    ConxDB db;
    JFrame fEditPatient;
    int classTime = 1;
    JTextField tDir = new JTextField(30);
    JTextField tEntre = new JTextField(20);
    JTextField tRef = new JTextField(20);
    JTextField tCol = new JTextField(20);
    JTextField tDel = new JTextField(20);
    JTextField tTransfer = new JTextField(8);
    JTextField tRadioOper = new JTextField(20);
    JTextField tApplicant = new JTextField(15);
    JTextField tCallmain = new JTextField(12);
    JTextField tNumPatient = new JTextField(5);
    JTextField tAlive = new JTextField(5);
    JTextField tDeads = new JTextField(5);
    JTextField tAgeOld = new JTextField(5);
    JTextField tNumFrap = new JTextField(5);
    JTextField tNamePatient = new JTextField(17);
    JTextField tLastNamePatient = new JTextField(13);
    JTextField tLastName2Patient = new JTextField(13);
    JTextField tAmbulance = new JTextField(5);
    JTextField tKmDeparture = new JTextField(5);
    JTextField tKmComeback = new JTextField(5);
    JTextField tFolio = new JTextField(5);
    JTextField tOperVoluntary = new JTextField(17);
    JTextField tParamedicVoluntary = new JTextField(17);
    JTextField tOper = new JTextField(17);
    JTextField tParamedic = new JTextField(17);
    JTextField tKmUsed = new JTextField(8);
    JTextField tTimeCall = new JTextField(8);
    JTextField tTimeDeparture = new JTextField(8);
    JTextField tTimeArrival = new JTextField(8);
    JTextField tTimeTransfer = new JTextField(8);
    JTextField tTimeHospital = new JTextField(8);
    JTextField tTimeComeback = new JTextField(8);
//    JRadioButton multSingle = new JRadioButton("Individual", false);
//    JRadioButton multGroup = new JRadioButton("Grupal", false);
    JRadioButton sexM = new JRadioButton("Mas", false);
    JRadioButton sexF = new JRadioButton("Fem", false);
    JRadioButton rAlive = new JRadioButton("Vivo", false);
    JRadioButton rDeads = new JRadioButton("Muerto", false);
    JButton bTime = new JButton("GUARDAR");
    JButton bSave = new JButton("Actualizar");
    JMenu mResultado = new JMenu("Resultado");
    JMenu mPriorityTransfer = new JMenu("Prioridad");
    JMenu mTransfer = new JMenu("Hospital");
    JMenu mOper = new JMenu();
    JMenu mRadioOper = new JMenu();
    JMenu mParamedic = new JMenu();
    JMenu mAmbulance = new JMenu();
    JMenu mBase = new JMenu("Base de salida");
    JMenu mCallmain = new JMenu("llamada recibida");
//    ButtonGroup groupMult = new ButtonGroup();
    ButtonGroup groupSex = new ButtonGroup();
    ButtonGroup groupStatus = new ButtonGroup();
    JTextArea tNote = new JTextArea(5, 30);
    JTextArea aPatient = new JTextArea(1, 30);
    JPanel pPatient = new JPanel();
    char[] cadena;
    ArrayList<String[]> patient = new ArrayList<String[]>();
    String tipeCallmain = "";
    String sTimeCall;
    String sex = "";
    String status = "";
    String numAmbulance = "";
    String nameParamedic = "";
    String nameOper = "";
    String nameRadioOper = "";
    int windowX = 0;
    int idAmbulance = 0;
    int idParamedic = 0;
    int idOper = 0;
    int idRadioOper = 0;
    int idPatient = 0;

    public int getWindowX() {
        return windowX;
    }

    public void setWindowX(int windowX) {
        this.windowX = windowX;
        //bTime.setPreferredSize(new Dimension(windowX / 13, 30));
        System.out.println(windowX + "\treWind");
        //ctrData(sTimeCall, windowX);
    }

    EditEmergency(int windowX, tabData tab, ConxDB db, int idEmergency) {
        this.idEmergency = idEmergency;
        this.windowX = windowX;
        this.tab = tab;
        this.db = db;
        String[] dataEmergency = db.consultEditEmergency(idEmergency);
        this.tDir.setText(dataEmergency[0]);
        this.tEntre.setText(dataEmergency[1]);
        this.tRef.setText(dataEmergency[2]);
        this.tCol.setText(dataEmergency[3]);
        this.tDel.setText(dataEmergency[4]);
        this.tApplicant.setText(dataEmergency[5]);
        this.mResultado.setText(dataEmergency[6]);
        this.mTransfer.setText(dataEmergency[7]);
        if (dataEmergency[8].equals("0")) {
            this.mPriorityTransfer.setText("Prioridad del traslado");
        } else {
            this.mPriorityTransfer.setText("Prioridad " + dataEmergency[8]);
        }
        this.tAlive.setText(dataEmergency[9]);
        this.tDeads.setText(dataEmergency[10]);
        this.idParamedic = Integer.valueOf(dataEmergency[11]);
        this.mParamedic.setText("PM#" + dataEmergency[11] + " " + db.consultParamedic(idParamedic));
        this.idOper = Integer.valueOf(dataEmergency[12]);
        this.mOper.setText("OP#" + dataEmergency[12] + " " + db.consultOper(idOper));
        this.idRadioOper = Integer.valueOf(dataEmergency[13]);
        this.mRadioOper.setText("RO#" + dataEmergency[13] + " " + db.consultRadioOper(idRadioOper));
        this.idAmbulance = Integer.valueOf(dataEmergency[14]);
        this.mAmbulance.setText(db.consultAmbulanceNum(idAmbulance));
        this.tKmUsed.setText(dataEmergency[15]);
        this.mBase.setText(dataEmergency[16]);
        this.tOperVoluntary.setText(dataEmergency[17]);
        this.tParamedicVoluntary.setText(dataEmergency[18]);
        this.tTimeCall.setText(dataEmergency[19]);
        this.tTimeDeparture.setText(dataEmergency[20]);
        this.tTimeArrival.setText(dataEmergency[21]);
        this.tTimeTransfer.setText(dataEmergency[22]);
        this.tTimeHospital.setText(dataEmergency[23]);
        this.tTimeComeback.setText(dataEmergency[24]);
        this.tNote.setText(dataEmergency[25]);
        this.tipeCallmain = dataEmergency[26];
        if(!dataEmergency[26].equals("")&&dataEmergency[26]!=null){
            mCallmain.setText(dataEmergency[26]);
        }
        tCallmain.setText(dataEmergency[27]);
        this.tFolio.setText(dataEmergency[28]);
        tab.tTransferFrom.setText(dataEmergency[29]);
        tab.tTransferTo.setText(dataEmergency[30]);
        aPatient.setText(db.consultPatientForEdit(idEmergency));

        JPanel pDir = new JPanel();
        JPanel pTransfer = new JPanel();
        JPanel pTimeAmbulance = new JPanel(new GridLayout(1, 13));
        pTimeAmbulance.setBackground(Color.cyan);

//        JLabel lSpace0 = new JLabel("");
//        JLabel lSpace1 = new JLabel("");
//        JLabel lSpace2 = new JLabel("");
//        JLabel lSpace3 = new JLabel("");
//        JLabel lSpace9 = new JLabel("");
        JLabel lDir = new JLabel("Ubicación:");
        JLabel lEntre = new JLabel("Entre:");
        JLabel lRef = new JLabel("Referencia:");
        JLabel lCol = new JLabel("Colonia:");
        JLabel lDel = new JLabel("Delegación:");
        JLabel lTimeCall = new JLabel("Hora llamada:");
        JLabel lTimeDeparture = new JLabel("Hora salida:");
        JLabel lTimeArrival = new JLabel("Hora llegada:");
        JLabel lTimeTransfer = new JLabel("Hora traslado:");
        JLabel lTimeHospital = new JLabel("Hora hospital:");
        JLabel lTimeComeback = new JLabel("Hora base:");
        JLabel lApplicant = new JLabel("Solicitante:");
        JLabel lRadioOper = new JLabel("Radio Operador:");
        JLabel lPatient = new JLabel("Paciente:");
        JLabel lSex = new JLabel("Sexo:");
        JLabel lAgeOld = new JLabel("Edad:");
        JLabel lAlive = new JLabel("Vivos:");
        JLabel lDeads = new JLabel("Muertos:");
        JLabel lNumFrap = new JLabel("Num frap:");
        JLabel lNamePatient = new JLabel("Nombre:");
        JLabel lLastNamePatient = new JLabel("Apellido paterno:");
        JLabel lLastName2Patient = new JLabel("Apellido materno:");
        JLabel lStatus = new JLabel("Estado:");
        JLabel lNumPatient = new JLabel("Numero de paciente CRM:");
        JLabel lAmbulance = new JLabel("Ambulancia:");
        JLabel lKmDeparture = new JLabel("Km:");
        JLabel lFolio = new JLabel("Folio:");
        JLabel lOperVoluntary = new JLabel("Operador voluntario:");
        JLabel lParamedicVoluntary = new JLabel("Paramedico voluntario:");
        JLabel lOper = new JLabel("Operador:");
        JLabel lParamedic = new JLabel("Paramedico:");
        JLabel lKmUsed = new JLabel("Km recorrido:");
        JLabel lNote = new JLabel("Observacion:");
        JMenuBar mBarResultado = new JMenuBar();
        JMenuBar mBarPriorityTransfer = new JMenuBar();
        JMenuBar mBarTransfer = new JMenuBar();
        JMenuBar mBarOper = new JMenuBar();
        JMenuBar mBarRadioOper = new JMenuBar();
        JMenuBar mBarParamedic = new JMenuBar();
        JMenuBar mBarAmbulance = new JMenuBar();
        JMenuBar mBarBase = new JMenuBar();
        JMenuBar mBarCallmain = new JMenuBar();
        JMenuItem iResultado1 = new JMenuItem("22 Falsa alarma");
        JMenuItem iResultado2 = new JMenuItem("22B No encontrado");
        JMenuItem iResultado3 = new JMenuItem("10X3 Tranf por tercero");
        JMenuItem iResultado4 = new JMenuItem("22A No necesita tranf");
        JMenuItem iResultado5 = new JMenuItem("22C Se niega a tranf");
        JMenuItem iResultado6 = new JMenuItem("10 Traslado");
        JMenuItem iResultado7 = new JMenuItem("14 Fallecido");
        JMenuItem iPriority1 = new JMenuItem("Prioridad 1");
        JMenuItem iPriority2 = new JMenuItem("Prioridad 2");
        JMenuItem iPriority3 = new JMenuItem("Prioridad 3");
        JMenuItem iTransfer1 = new JMenuItem("CRUZ ROJA");
        JMenuItem iTransfer2 = new JMenuItem("SM DDF");
        JMenuItem iTransfer3 = new JMenuItem("ISSSTE");
        JMenuItem iTransfer4 = new JMenuItem("IMSS");
        JMenuItem iTransfer5 = new JMenuItem("SSA");
        JMenuItem iTransfer6 = new JMenuItem("PEMEX");
        JMenuItem iTransfer7 = new JMenuItem("MILITAR");
        JMenuItem iTransfer8 = new JMenuItem("NAVAL");
        JMenuItem iTransfer9 = new JMenuItem("PRIVADO");
        JMenuItem iTransfer10 = new JMenuItem("OTRO");
        JMenuItem iBase1 = new JMenuItem("Centro");
        JMenuItem iBase2 = new JMenuItem("Norte");
        JMenuItem iBase3 = new JMenuItem("Boca");
        JMenuItem iCallmain1 = new JMenuItem("Tel particular");
        JMenuItem iCallmain2 = new JMenuItem("Tel celular");
        JMenuItem iCallmain3 = new JMenuItem("Presentado");
        JMenuItem iCallmain4 = new JMenuItem("Quien hizo la llamada");
        JMenuItem iCallmain5 = new JMenuItem("Casetar");
        JMenuItem iCallmain6 = new JMenuItem("Radio");
        JMenuItem iCallmain7 = new JMenuItem("Pre base");
        JMenuItem iCallmain8 = new JMenuItem("otros");
        JButton bUpPatient = new JButton(" + ");
        JButton bEditPatient = new JButton("Editar Paciente");
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tNote);
        iResultado1.addActionListener(this);
        iResultado2.addActionListener(this);
        iResultado3.addActionListener(this);
        iResultado4.addActionListener(this);
        iResultado5.addActionListener(this);
        iResultado6.addActionListener(this);
        iResultado7.addActionListener(this);
        iPriority1.addActionListener(this);
        iPriority2.addActionListener(this);
        iPriority3.addActionListener(this);
        iTransfer1.addActionListener(this);
        iTransfer2.addActionListener(this);
        iTransfer3.addActionListener(this);
        iTransfer4.addActionListener(this);
        iTransfer5.addActionListener(this);
        iTransfer6.addActionListener(this);
        iTransfer7.addActionListener(this);
        iTransfer8.addActionListener(this);
        iTransfer9.addActionListener(this);
        iTransfer10.addActionListener(this);
        iBase1.addActionListener(this);
        iBase2.addActionListener(this);
        iBase3.addActionListener(this);
        sexM.addActionListener(this);
        sexF.addActionListener(this);
        rAlive.addActionListener(this);
        rDeads.addActionListener(this);
        bTime.addActionListener(this);
        bSave.addActionListener(this);
        bUpPatient.addActionListener(this);
        bEditPatient.addActionListener(this);
        iCallmain1.addActionListener(this);
        iCallmain2.addActionListener(this);
        iCallmain3.addActionListener(this);
        iCallmain4.addActionListener(this);
        iCallmain5.addActionListener(this);
        iCallmain6.addActionListener(this);
        iCallmain7.addActionListener(this);
        iCallmain8.addActionListener(this);
        mResultado.add(iResultado1);
        mResultado.add(iResultado2);
        mResultado.add(iResultado3);
        mResultado.add(iResultado4);
        mResultado.add(iResultado5);
        mResultado.add(iResultado6);
        mResultado.add(iResultado7);
        mBarResultado.add(mResultado);
        mPriorityTransfer.add(iPriority1);
        mPriorityTransfer.add(iPriority2);
        mPriorityTransfer.add(iPriority3);
        mBarPriorityTransfer.add(mPriorityTransfer);
        mTransfer.add(iTransfer1);
        mTransfer.add(iTransfer2);
        mTransfer.add(iTransfer3);
        mTransfer.add(iTransfer4);
        mTransfer.add(iTransfer5);
        mTransfer.add(iTransfer6);
        mTransfer.add(iTransfer7);
        mTransfer.add(iTransfer8);
        mTransfer.add(iTransfer9);
        mTransfer.add(iTransfer10);
        mBarTransfer.add(mTransfer);
        mBase.add(iBase1);
        mBase.add(iBase2);
        mBase.add(iBase3);
        mBarBase.add(mBase);
        mCallmain.add(iCallmain1);
        mCallmain.add(iCallmain2);
        mCallmain.add(iCallmain3);
        mCallmain.add(iCallmain4);
        mCallmain.add(iCallmain5);
        mCallmain.add(iCallmain6);
        mCallmain.add(iCallmain7);
        mCallmain.add(iCallmain8);
        mBarCallmain.add(mCallmain);
        groupSex.add(sexM);
        groupSex.add(sexF);
        groupStatus.add(rAlive);
        groupStatus.add(rDeads);
        menuUpdate();
        mBarOper.add(mOper);
        mBarRadioOper.add(mRadioOper);
        mBarParamedic.add(mParamedic);
        mBarAmbulance.add(mAmbulance);
        mOper.setPreferredSize(new Dimension(250, 20));
        mRadioOper.setPreferredSize(new Dimension(200, 20));
        mParamedic.setPreferredSize(new Dimension(250, 20));
        mAmbulance.setPreferredSize(new Dimension(150, 20));
        lDir.setPreferredSize(new Dimension(60, 50));
        lAlive.setPreferredSize(new Dimension(60, 50));
        lDeads.setPreferredSize(new Dimension(60, 50));
//        lSpace0.setPreferredSize(new Dimension(25, 50));
//        lSpace1.setPreferredSize(new Dimension(25, 50));
//        lSpace2.setPreferredSize(new Dimension(50, 50));
//        lSpace9.setPreferredSize(new Dimension(100, 50));
        lSex.setPreferredSize(new Dimension(50, 50));
        lAgeOld.setPreferredSize(new Dimension(50, 50));
        lNamePatient.setPreferredSize(new Dimension(65, 50));
        lLastNamePatient.setPreferredSize(new Dimension(115, 50));
        lLastName2Patient.setPreferredSize(new Dimension(115, 50));
        lStatus.setPreferredSize(new Dimension(65, 50));
        lNumPatient.setPreferredSize(new Dimension(170, 50));
        lAmbulance.setPreferredSize(new Dimension(170, 50));
        lKmDeparture.setPreferredSize(new Dimension(70, 50));
        lOper.setPreferredSize(new Dimension(120, 50));
        lParamedic.setPreferredSize(new Dimension(120, 50));
        lOperVoluntary.setPreferredSize(new Dimension(170, 50));
        lParamedicVoluntary.setPreferredSize(new Dimension(170, 50));
        lNote.setPreferredSize(new Dimension(100, 50));
        bTime.setPreferredSize(new Dimension(windowX / 13, 30));
        mResultado.setPreferredSize(new Dimension(150, 30));
        mPriorityTransfer.setPreferredSize(new Dimension(100, 30));
        mCallmain.setPreferredSize(new Dimension(130, 30));
        iPriority1.setPreferredSize(new Dimension(100, 20));
        iPriority2.setPreferredSize(new Dimension(100, 20));
        iPriority3.setPreferredSize(new Dimension(100, 20));
        mTransfer.setPreferredSize(new Dimension(100, 30));
        iTransfer1.setPreferredSize(new Dimension(100, 20));
        iTransfer2.setPreferredSize(new Dimension(100, 20));
        iTransfer3.setPreferredSize(new Dimension(100, 20));
        iTransfer4.setPreferredSize(new Dimension(100, 20));
        iTransfer5.setPreferredSize(new Dimension(100, 20));
        iTransfer6.setPreferredSize(new Dimension(100, 20));
        iTransfer7.setPreferredSize(new Dimension(100, 20));
        iTransfer8.setPreferredSize(new Dimension(100, 20));
        iTransfer9.setPreferredSize(new Dimension(100, 20));
        iTransfer10.setPreferredSize(new Dimension(100, 20));
        mBase.setPreferredSize(new Dimension(150, 30));
        iBase1.setPreferredSize(new Dimension(150, 20));
        iBase2.setPreferredSize(new Dimension(150, 20));
        iBase3.setPreferredSize(new Dimension(150, 20));
        lDir.setHorizontalAlignment(SwingConstants.RIGHT);
        lRadioOper.setHorizontalAlignment(SwingConstants.RIGHT);
        lAlive.setHorizontalAlignment(SwingConstants.RIGHT);
        lDeads.setHorizontalAlignment(SwingConstants.RIGHT);
        lNamePatient.setHorizontalAlignment(SwingConstants.RIGHT);
        lLastNamePatient.setHorizontalAlignment(SwingConstants.RIGHT);
        lLastName2Patient.setHorizontalAlignment(SwingConstants.RIGHT);
        lSex.setHorizontalAlignment(SwingConstants.RIGHT);
        lAgeOld.setHorizontalAlignment(SwingConstants.RIGHT);
        lStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        lNumPatient.setHorizontalAlignment(SwingConstants.RIGHT);
        lAmbulance.setHorizontalAlignment(SwingConstants.RIGHT);
        lKmDeparture.setHorizontalAlignment(SwingConstants.RIGHT);
        lOper.setHorizontalAlignment(SwingConstants.RIGHT);
        lParamedic.setHorizontalAlignment(SwingConstants.RIGHT);
        lOperVoluntary.setHorizontalAlignment(SwingConstants.RIGHT);
        lParamedicVoluntary.setHorizontalAlignment(SwingConstants.RIGHT);
        lNote.setHorizontalAlignment(SwingConstants.RIGHT);
        tTimeCall.setHorizontalAlignment(JTextField.CENTER);
        tTimeDeparture.setHorizontalAlignment(JTextField.CENTER);
        tTimeArrival.setHorizontalAlignment(JTextField.CENTER);
        tTimeTransfer.setHorizontalAlignment(JTextField.CENTER);
        tTimeHospital.setHorizontalAlignment(JTextField.CENTER);
        tTimeComeback.setHorizontalAlignment(JTextField.CENTER);
        mResultado.setHorizontalAlignment(SwingConstants.CENTER);
        mResultado.setHorizontalTextPosition(JTextField.CENTER);
        lTimeCall.setHorizontalAlignment(JTextField.RIGHT);
        lTimeDeparture.setHorizontalAlignment(JTextField.RIGHT);
        lTimeArrival.setHorizontalAlignment(JTextField.RIGHT);
        lTimeTransfer.setHorizontalAlignment(JTextField.RIGHT);
        lTimeHospital.setHorizontalAlignment(JTextField.RIGHT);
        lTimeComeback.setHorizontalAlignment(JTextField.RIGHT);
        bTime.setVisible(false);
        tNote.setLineWrap(true);
        tNote.setWrapStyleWord(true);

        this.setLayout(new FlowLayout());

         pDir.add(lDir);
        pDir.add(tDir);
        pDir.add(lEntre);
        pDir.add(tEntre);
        pDir.add(lRef);
        pDir.add(tRef);
        pDir.add(lCol);
        pDir.add(tCol);
        pDir.add(lDel);
        pDir.add(tDel);
        this.add(pDir);
        pTransfer.add(lRadioOper);
        pTransfer.add(mBarRadioOper);
        pTransfer.add(lApplicant);
        pTransfer.add(tApplicant);
        pTransfer.add(mBarCallmain);
        pTransfer.add(tCallmain);
        pTransfer.add(mBarResultado);
        pTransfer.add(mBarPriorityTransfer);
        pTransfer.add(mBarTransfer);
        pTransfer.add(tTransfer);
        this.add(pTransfer);
        this.add(lAlive);
        this.add(tAlive);
        this.add(lDeads);
        this.add(tDeads);
//        this.add(lSpace2);
        this.add(lPatient);
        this.add(lStatus);
        this.add(rAlive);
        this.add(rDeads);
        this.add(lSex);
        this.add(sexM);
        this.add(sexF);
        this.add(lAgeOld);
        this.add(tAgeOld);
        this.add(lNumFrap);
        this.add(tNumFrap);
        this.add(lNamePatient);
        this.add(tNamePatient);
        this.add(lLastNamePatient);
        this.add(tLastNamePatient);
        this.add(lLastName2Patient);
        this.add(tLastName2Patient);
        this.add(bUpPatient);
//        this.add(lSpace3);
        this.add(lAmbulance);
        this.add(mBarAmbulance);
        this.add(lKmDeparture);
        this.add(tKmDeparture);
        this.add(mBarBase);
        this.add(lFolio);
        this.add(tFolio);
        this.add(lOper);
        this.add(mBarOper);
        this.add(lParamedic);
        this.add(mBarParamedic);
//        this.add(lSpace9);
        this.add(lOperVoluntary);
        this.add(tOperVoluntary);
        this.add(lParamedicVoluntary);
        this.add(tParamedicVoluntary);
        this.add(lKmUsed);
        this.add(tKmUsed);
        this.add(lNote);
        this.add(scroll, BorderLayout.CENTER);
        this.add(bSave);
        pTimeAmbulance.add(bTime);
        pTimeAmbulance.add(lTimeCall);
        pTimeAmbulance.add(tTimeCall);
        pTimeAmbulance.add(lTimeDeparture);
        pTimeAmbulance.add(tTimeDeparture);
        pTimeAmbulance.add(lTimeArrival);
        pTimeAmbulance.add(tTimeArrival);
        pTimeAmbulance.add(lTimeTransfer);
        pTimeAmbulance.add(tTimeTransfer);
        pTimeAmbulance.add(lTimeHospital);
        pTimeAmbulance.add(tTimeHospital);
        pTimeAmbulance.add(lTimeComeback);
        pTimeAmbulance.add(tTimeComeback);
        this.add(pTimeAmbulance, BorderLayout.SOUTH);
        pPatient.add(aPatient);
        pPatient.add(bEditPatient);
        this.add(pPatient);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] cadena = e.getActionCommand().toCharArray();
        String command = "";
        if (e.getActionCommand().length() > 1) {
            command = "" + cadena[0] + cadena[1];
        }
        switch (e.getSource().getClass().getSimpleName()) {
            case "JRadioButton":
                switch (e.getActionCommand()) {
                    case "Vivo":
                        status = e.getActionCommand();
                        break;
                    case "Muerto":
                        status = e.getActionCommand();
                        break;
                    case "Mas":
                        sex = e.getActionCommand();
                        break;
                    case "Fem":
                        sex = e.getActionCommand();
                        break;
                    default:
                        System.out.println("El event JRadioButton es: " + e.getActionCommand());
                        break;
                }
                break;
            case "JMenuItem":
                switch (e.getActionCommand()) {
                    case "22 Falsa alarma":
                    case "22B No encontrado":
                    case "10X3 Tranf por tercero":
                    case "22A No necesita tranf":
                    case "22C Se niega a tranf":
                    case "14 Fallecido":
                    case "10 Traslado":
                        mResultado.setText(e.getActionCommand());
                        break;
                    case "Centro":
                    case "Norte":
                    case "Boca":
                        mBase.setText(e.getActionCommand());
                        break;
                    case "Prioridad 1":
                    case "Prioridad 2":
                    case "Prioridad 3":
                        mPriorityTransfer.setText(e.getActionCommand());
                        break;
                    case "CRUZ ROJA":
                    case "SM DDF":
                    case "ISSSTE":
                    case "IMSS":
                    case "SSA":
                    case "PEMEX":
                    case "MILITAR":
                    case "NAVAL":
                    case "PRIVADO":
                    case "OTRO":
                        mTransfer.setText(e.getActionCommand());
                        break;
                    case "Tel particular":
                    case "Tel celular":
                    case "Presentado":
                    case "Quien hizo la llamada":
                    case "Casetar":
                    case "Radio":
                    case "Pre base":
                    case "otros":
                        mCallmain.setText(e.getActionCommand());
                        tipeCallmain = e.getActionCommand();
                        break;
                    default:
                        if (cadena[2] == '#') {
                            String word;
                            boolean isId = true;
                            switch (command) {
                                case "AB":
                                    mAmbulance.setText(e.getActionCommand());
                                    word = "";
                                    cadena = e.getActionCommand().toCharArray();
                                    if (cadena[0] == 'A' && cadena[1] == 'B') {
                                        for (int i = 3; i < cadena.length; i++) {
                                            if (cadena[i] == ' ') {
                                                if (isId) {
                                                    this.idAmbulance = Integer.valueOf(word);
                                                    word = "";
                                                    isId = false;
                                                } else {
                                                    word += cadena[i];
                                                }
                                            } else {
                                                word += cadena[i];
                                            }
                                        }
                                    }
                                    numAmbulance = word;
                                    tKmDeparture.setText(String.valueOf(db.consultAmbulanceKm(idAmbulance)));
                                    break;
                                case "OP":
                                    mOper.setText(e.getActionCommand());
                                    word = "";
                                    cadena = e.getActionCommand().toCharArray();
                                    if (cadena[0] == 'O' && cadena[1] == 'P') {
                                        for (int i = 3; i < cadena.length; i++) {
                                            if (cadena[i] == ' ') {
                                                if (isId) {
                                                    this.idOper = Integer.valueOf(word);
                                                    word = "";
                                                    isId = false;
                                                } else {
                                                    word += cadena[i];
                                                }
                                            } else {
                                                word += cadena[i];
                                            }
                                        }
                                    }
                                    nameOper = word;
                                    break;
                                case "RO":
                                    mRadioOper.setText(e.getActionCommand());
                                    word = "";
                                    cadena = e.getActionCommand().toCharArray();
                                    if (cadena[0] == 'R' && cadena[1] == 'O') {
                                        for (int i = 3; i < cadena.length; i++) {
                                            if (cadena[i] == ' ') {
                                                if (isId) {
                                                    this.idRadioOper = Integer.valueOf(word);
                                                    word = "";
                                                    isId = false;
                                                } else {
                                                    word += cadena[i];
                                                }
                                            } else {
                                                word += cadena[i];
                                            }
                                        }
                                    }
                                    nameRadioOper = word;
                                    break;
                                case "PM":
                                    mParamedic.setText(e.getActionCommand());
                                    word = "";
                                    cadena = e.getActionCommand().toCharArray();
                                    if (cadena[0] == 'P' && cadena[1] == 'M') {
                                        for (int i = 3; i < cadena.length; i++) {
                                            if (cadena[i] == ' ') {
                                                if (isId) {
                                                    this.idParamedic = Integer.valueOf(word);
                                                    word = "";
                                                    isId = false;
                                                } else {
                                                    word += cadena[i];
                                                }
                                            } else {
                                                word += cadena[i];
                                            }
                                        }
                                    }
                                    nameParamedic = word;
                                    break;
                            }
                        }
                }
                break;
            case "JButton":
                switch (e.getActionCommand()) {
                    case " + ":
                        String preLPatient = aPatient.getText();
                        String[] arrayPatient = new String[7];
                        arrayPatient[0] = tNamePatient.getText();
                        arrayPatient[1] = tLastNamePatient.getText();
                        arrayPatient[2] = tLastName2Patient.getText();
                        int ageOld = 0;
                        if (!tAgeOld.getText().equals("") && tAgeOld.getText() != null) {
                            ageOld = Integer.valueOf(tAgeOld.getText());
                        }
                        arrayPatient[3] = ageOld + "";
                        arrayPatient[4] = sex;
                        arrayPatient[5] = status;
                        String FRAP = "0";
                        if (!tNumFrap.getText().equals("") && tNumFrap.getText() != null) {
                            FRAP = tNumFrap.getText();
                        }
                        arrayPatient[6] = FRAP;
                        patient.add(arrayPatient);
                        preLPatient += "Estado: " + status + " \tsexo: " + sex + "\tedad: " + ageOld
                                + "\tNumFRAP: " + FRAP + " \tNombre: " + tNamePatient.getText() + " "
                                + tLastNamePatient.getText() + " " + tLastName2Patient.getText() + "\n";
                        aPatient.setText(preLPatient);
                        groupSex.clearSelection();
                        groupStatus.clearSelection();
                        tAgeOld.setText("");
                        tNumFrap.setText("");
                        tNamePatient.setText("");
                        tLastNamePatient.setText("");
                        tLastName2Patient.setText("");
                        break;
                    case "Editar Paciente":
                        JPanel InsertId = new JPanel();
                        JTextField tId = new JTextField(8);
                        InsertId.add(new JLabel("ID del Paceinte:"));
                        InsertId.add(tId);
                        if (0 == JOptionPane.showConfirmDialog(null, InsertId, "login", JOptionPane.DEFAULT_OPTION)) {
                            JFrame patient = new JFrame("Paciente #" + tId.getText());
                            patient.setSize(470, 700);
                            patient.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - patient.getWidth() / 2,
                                    (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - patient.getHeight() / 2);
                            patient.setFocusable(true);
                            patient.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/cruzroja.png"));
                            patient.add(new EditPatient(patient, db, Integer.valueOf(tId.getText())));
                            patient.setVisible(true);
                        }
                        break;
                    case "Actualizar":
                        int obstetricoMonthes = 0;
                        if (!tab.tObstetricoMonthes.getText().equals("") && tab.tObstetricoMonthes.getText() != null) {
                            obstetricoMonthes = Integer.valueOf(tab.tObstetricoMonthes.getText());
                        }
                        String sPatient = "";
                        String obstetrico = "";
                        String trauma = "";
                        int alive = 0;
                        if (!tAlive.getText().equals("") && tAlive.getText() != null) {
                            alive = Integer.valueOf(tAlive.getText());
                        }
                        int deads = 0;
                        if (!tDeads.getText().equals("") && tDeads.getText() != null) {
                            deads = Integer.valueOf(tDeads.getText());
                        }
                        int priority = 0;
                        if (!mPriorityTransfer.getText().equals("Prioridad del traslado")) {
                            priority = Integer.valueOf("" + mPriorityTransfer.getText().toCharArray()[10]);
                        }
                        String transfer = "";
                        if (!mTransfer.getText().equals("Hospital a transferir")) {
                            transfer = mTransfer.getText() + " " + tTransfer.getText();
                        }
                        String resultado = "";
                        String word = "";
                        cadena = mResultado.getText().toCharArray();
                        for (int i = 0; i < cadena.length; i++) {
                            if (cadena[i] == ' ') {
                                i = cadena.length;
                            } else {
                                word += cadena[i];
                            }
                        }
                        resultado = word;
                        String em = db.editEmergency(idEmergency, tDir.getText(), tEntre.getText(), tRef.getText(),
                                tCol.getText(), tDel.getText(), tApplicant.getText(), resultado,
                                transfer, priority, alive, deads, idParamedic, idOper,
                                idRadioOper, idAmbulance, mBase.getText(), tOperVoluntary.getText(), tParamedicVoluntary.getText(),
                                tTimeCall.getText(), tTimeDeparture.getText(), tTimeArrival.getText(),
                                tTimeTransfer.getText(), tTimeHospital.getText(), tTimeComeback.getText(),
                                tNote.getText(), tipeCallmain, tCallmain.getText(), Integer.valueOf(tKmUsed.getText()), tFolio.getText(),
                                tab.tTransferFrom.getText(), tab.tTransferTo.getText());
                        System.out.println("insert= " + em);
                        if (em.equals("done")) {
                            for (int m = 0; m < patient.size(); m++) {
                                if (!tab.mObstetrico.getText().equals("Tipo de obstetrico")) {
                                    obstetrico = tab.mObstetrico.getText();
                                }
                                if (!tab.mTrauma.getText().equals("Tipo de Trauma")) {
                                    if (tab.mTrauma.getText().equals("Otro")) {
                                        trauma = tab.tOther.getText();
                                    } else {
                                        trauma = tab.mTrauma.getText();
                                    }
                                }
                                sPatient = db.insertPatient(patient.get(m)[0], patient.get(m)[1],
                                        patient.get(m)[2], Integer.valueOf(patient.get(m)[3]), patient.get(m)[4],
                                        patient.get(m)[5], patient.get(m)[6], idEmergency, trauma,
                                        tab.tMotivo.getText(), tab.tPadecimiento.getText(), tab.tMedicamento.getText(),
                                        tab.tEventoPrevio.getText(), obstetrico, obstetricoMonthes);
                                System.out.println("id Paciente: " + sPatient);
                            }
                        } else {
                            System.out.println("ctrData/ActionPerformed: error# " + em);
                        }
                        archivo a = new archivo();
                        String[][] data = new String[33][2];
                        data[0][0] = "date";
                        data[1][0] = "tiket";
                        data[2][0] = "dir";
                        data[3][0] = "between";
                        data[4][0] = "sector";
                        data[5][0] = "zone";
                        data[6][0] = "callmain";
                        data[7][0] = "service";
                        data[8][0] = "priority";
                        data[9][0] = "ambulance";
                        data[10][0] = "base";
                        data[11][0] = "Timecall";
                        data[12][0] = "Timedeparture";
                        data[13][0] = "Timearrival";
                        data[14][0] = "Timetransfer";
                        data[15][0] = "Timehospital";
                        data[16][0] = "Timecomeback";
                        data[17][0] = "Calldeparture";
                        data[18][0] = "Departurearrival";
                        data[19][0] = "Arrivaltransfer";
                        data[20][0] = "Transferhospital";
                        data[21][0] = "Hospitalcomeback";
                        data[22][0] = "alive";
                        data[23][0] = "dead";
                        data[24][0] = "patient";
                        data[25][0] = "age";
                        data[26][0] = "sex";
                        data[27][0] = "status";
                        data[28][0] = "transfer";
                        data[29][0] = "denominacion";
                        data[30][0] = "note";
                        data[31][0] = "telephone";
                        data[32][0] = "radiooper";
                        data[0][1] = db.divTimeFull(tTimeCall.getText(), false);
                        data[1][1] = tFolio.getText();
                        data[2][1] = tDir.getText();
                        data[3][1] = tEntre.getText();
                        data[4][1] = tCol.getText();
                        data[5][1] = tDel.getText();
                        data[6][1] = tipeCallmain + " " + tCallmain.getText();
                        switch (tab.mTrauma.getText()) {
                            case "Tipo de Trauma":
                                data[7][1] = "";
                                break;
                            case "Otro":
                                data[7][1] = tab.tOther.getText();
                                break;
                            default:
                                data[7][1] = tab.mTrauma.getText();
                        }
                        if (priority != 0) {
                            data[8][1] = mPriorityTransfer.getText();
                        } else {
                            data[8][1] = "";
                        }
                        data[9][1] = numAmbulance;
                        data[10][1] = mBase.getText();
                        data[11][1] = db.divTimeFull(tTimeCall.getText(), true);
                        data[12][1] = db.divTimeFull(tTimeDeparture.getText(), true);
                        data[13][1] = db.divTimeFull(tTimeArrival.getText(), true);
                        data[14][1] = db.divTimeFull(tTimeTransfer.getText(), true);
                        data[15][1] = db.divTimeFull(tTimeHospital.getText(), true);
                        data[16][1] = db.divTimeFull(tTimeComeback.getText(), true);
                        data[17][1] = db.subTime(data[11][1], data[12][1]);
                        data[18][1] = db.subTime(data[12][1], data[13][1]);
                        data[19][1] = db.subTime(data[13][1], data[14][1]);
                        if (data[15][1].equals("00:00:00") || data[15][1].equals("0:00:00")) {
                            data[20][1] = "";
                            data[21][1] = db.subTime(data[14][1], data[16][1]);
                        } else {
                            data[20][1] = db.subTime(data[14][1], data[15][1]);
                            data[21][1] = db.subTime(data[15][1], data[16][1]);
                        }
                        data[22][1] = tAlive.getText();
                        data[23][1] = tDeads.getText();
                        if (patient.size() > 0) {
                            data[24][1] = patient.get(0)[0] + " " + patient.get(0)[1] + " " + patient.get(0)[2];
                            data[25][1] = patient.get(0)[3];
                            data[26][1] = patient.get(0)[4];
                            data[27][1] = patient.get(0)[5];
                        } else {
                            data[24][1] = "";
                            data[25][1] = "";
                            data[26][1] = "";
                            data[27][1] = "";
                        }
                        data[28][1] = transfer;
                        data[29][1] = "";
                        data[30][1] = tNote.getText();
                        data[31][1] = "";
                        data[32][1] = db.consultRadioOper(idRadioOper);

                        a.replaceWordData("resource/formatoCtrlAmb.docx", "C:/CtrlAmb/Emergencia#" + idEmergency + ".docx", data);
                        try {
                            Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\Emergencia#" + idEmergency + ".docx");
                        } catch (IOException ex) {
                            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
        }
    }

    public void menuUpdate() {
        char[] cadena;
        String employees = db.consultOper();
        cadena = employees.toCharArray();
        String word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                iEmployees.setPreferredSize(new Dimension(250, 20));
                mOper.add(iEmployees);
                iEmployees.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
        employees = db.consultRadioOper();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                iEmployees.setPreferredSize(new Dimension(200, 20));
                mRadioOper.add(iEmployees);
                iEmployees.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
        employees = db.consultParamedic();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                iEmployees.setPreferredSize(new Dimension(250, 20));
                mParamedic.add(iEmployees);
                iEmployees.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
        employees = db.consultAmbulanceNum();
        cadena = employees.toCharArray();
        word = "";
        for (int i = 0; i < employees.length(); i++) {
            if (cadena[i] == '\n') {
                JMenuItem iEmployees = new JMenuItem(word);
                iEmployees.setPreferredSize(new Dimension(150, 20));
                mAmbulance.add(iEmployees);
                iEmployees.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }
}
