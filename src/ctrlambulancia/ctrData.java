/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class ctrData extends JPanel implements ActionListener {

    JFrame emergency;
    tabData tab;
    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat timeFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    ConxDB db;
    int classTime = 1;
    JTextField tDir = new JTextField(30);
    JTextField tEntre = new JTextField(20);
    JTextField tRef = new JTextField(20);
    JTextField tCol = new JTextField(20);
    JTextField tDel = new JTextField(20);
    JTextField tTransfer = new JTextField(8);
    JTextField tRadioOper = new JTextField(20);
    JTextField tApplicant = new JTextField(20);
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
    JTextField tTimeCall = new JTextField(8);
    JTextField tTimeDeparture = new JTextField(8);
    JTextField tTimeArrival = new JTextField(8);
    JTextField tTimeTransfer = new JTextField(8);
    JTextField tTimeHospital = new JTextField(8);
    JTextField tTimeComeback = new JTextField(8);
    JRadioButton multSingle = new JRadioButton("Individual", false);
    JRadioButton multGroup = new JRadioButton("Grupal", false);
    JRadioButton sexM = new JRadioButton("Mas", false);
    JRadioButton sexF = new JRadioButton("Fem", false);
    JRadioButton rAlive = new JRadioButton("Vivo", false);
    JRadioButton rDeads = new JRadioButton("Muerto", false);
    JButton bTime = new JButton("Hora salida");
    JMenu mResultado = new JMenu("Resultado");
    JMenu mPriorityTransfer = new JMenu("Prioridad del traslado");
    JMenu mTransfer = new JMenu("Hospital a transferir");
    JMenu mOper = new JMenu();
    JMenu mRadioOper = new JMenu();
    JMenu mParamedic = new JMenu();
    JMenu mAmbulance = new JMenu();
    JMenu mBase = new JMenu("Base de salida");
    ButtonGroup groupMult = new ButtonGroup();
    ButtonGroup groupSex = new ButtonGroup();
    ButtonGroup groupStatus = new ButtonGroup();
    JTextArea tNote = new JTextArea(5, 30);
    JTextArea aPatient = new JTextArea(1, 30);
    JPanel pPatient = new JPanel();
    char[] cadena;
    ArrayList<String[]> patient = new ArrayList<String[]>();
    String tipeCallmain = "";
    String callmain = "";
    String sTimeCall;
    String sex = "";
    String status = "";
    String timeCall = "";
    String timeDeparture = "";
    String timeArrival = "";
    String timeTransfer = "";
    String timeHospital = "";
    String timeComeback = "";
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

    public ctrData(String sTimeCall, int windowX, JFrame emergency, tabData tab, ConxDB db, String sAmbulance, String sKmAmbulance,
            String sBase, String sOperVoluntary, String sParamedicVoluntary, String sOper, String sParamedic,
            String sRadioOper, String sApplicant, String tipeCallmain, String callmain) {
        this.timeCall = sTimeCall;
        this.windowX = windowX;
        this.emergency = emergency;
        this.tab = tab;
        this.db = db;
        mAmbulance.setText(sAmbulance);
        tKmDeparture.setText(sKmAmbulance);
        mBase.setText(sBase);
        tOperVoluntary.setText(sOperVoluntary);
        tParamedicVoluntary.setText(sParamedicVoluntary);
        mOper.setText(sOper);
        mParamedic.setText(sParamedic);
        mRadioOper.setText(sRadioOper);
        tApplicant.setText(sApplicant);
        JPanel pDir = new JPanel();
        JPanel pTransfer = new JPanel();
        JPanel pTimeAmbulance = new JPanel(new GridLayout(1, 13));
        pTimeAmbulance.setBackground(Color.cyan);
        cadena = timeCall.toCharArray();
        sTimeCall = "";
        for (int i = 11; i < cadena.length; i++) {
            sTimeCall += cadena[i];
        }
        String word = "";
        boolean isId = true;
        cadena = sAmbulance.toCharArray();
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
        isId = true;
        this.numAmbulance = word;
        word = "";
        cadena = sParamedic.toCharArray();
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
        isId = true;
        this.nameParamedic = word;
        word = "";
        cadena = sOper.toCharArray();
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
        isId = true;
        this.nameOper = word;
        word = "";
        cadena = sRadioOper.toCharArray();
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
        this.nameRadioOper = word;
        if (!tipeCallmain.equals("llamada recibida")) {
            this.tipeCallmain = tipeCallmain;
        }
        this.callmain = callmain;

        JLabel lSpace0 = new JLabel("");
        JLabel lSpace1 = new JLabel("");
        JLabel lSpace2 = new JLabel("");
        JLabel lSpace3 = new JLabel("");
        JLabel lSpace9 = new JLabel("");
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
        //JLabel lKmComeback = new JLabel("Km llegada:");
        JLabel lFolio = new JLabel("Folio:");
        JLabel lOperVoluntary = new JLabel("Operador voluntario:");
        JLabel lParamedicVoluntary = new JLabel("Paramedico voluntario:");
        JLabel lOper = new JLabel("Operador:");
        JLabel lParamedic = new JLabel("Paramedico:");
        JLabel lNote = new JLabel("Observacion:");
        JMenuBar mBarResultado = new JMenuBar();
        JMenuBar mBarPriorityTransfer = new JMenuBar();
        JMenuBar mBarTransfer = new JMenuBar();
        JMenuBar mBarOper = new JMenuBar();
        JMenuBar mBarRadioOper = new JMenuBar();
        JMenuBar mBarParamedic = new JMenuBar();
        JMenuBar mBarAmbulance = new JMenuBar();
        JMenuBar mBarBase = new JMenuBar();
        JMenuItem iFalseAlarm = new JMenuItem("Falsa alarma");
        JMenuItem iNoFound = new JMenuItem("No encontrado");
        JMenuItem iOtherTransfer = new JMenuItem("Tranf por tercero");
        JMenuItem iNoNeedTransfer = new JMenuItem("No necesita tranf");
        JMenuItem iNoWantTransfer = new JMenuItem("No quiere tranf");
        JMenuItem iTransfer = new JMenuItem("Traslado");
        JMenuItem iDead = new JMenuItem("Fallecido");
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
        JButton bUpPatient = new JButton(" + ");
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(tNote);
        iFalseAlarm.addActionListener(this);
        iNoFound.addActionListener(this);
        iOtherTransfer.addActionListener(this);
        iNoNeedTransfer.addActionListener(this);
        iNoWantTransfer.addActionListener(this);
        iTransfer.addActionListener(this);
        iDead.addActionListener(this);
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
        multSingle.addActionListener(this);
        multGroup.addActionListener(this);
        sexM.addActionListener(this);
        sexF.addActionListener(this);
        rAlive.addActionListener(this);
        rDeads.addActionListener(this);
        bTime.addActionListener(this);
        bUpPatient.addActionListener(this);
        mResultado.add(iFalseAlarm);
        mResultado.add(iNoFound);
        mResultado.add(iOtherTransfer);
        mResultado.add(iNoNeedTransfer);
        mResultado.add(iNoWantTransfer);
        mResultado.add(iTransfer);
        mResultado.add(iDead);
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
        groupMult.add(multSingle);
        groupMult.add(multGroup);
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
        mRadioOper.setPreferredSize(new Dimension(250, 20));
        mParamedic.setPreferredSize(new Dimension(250, 20));
        mAmbulance.setPreferredSize(new Dimension(150, 20));
        lDir.setPreferredSize(new Dimension(60, 50));
        //lRadioOper.setPreferredSize(new Dimension(120, 50));
        lAlive.setPreferredSize(new Dimension(60, 50));
        lDeads.setPreferredSize(new Dimension(60, 50));
        lSpace0.setPreferredSize(new Dimension(25, 50));
        lSpace1.setPreferredSize(new Dimension(25, 50));
        lSpace2.setPreferredSize(new Dimension(100, 50));
        lSpace9.setPreferredSize(new Dimension(200, 50));
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
        mPriorityTransfer.setPreferredSize(new Dimension(150, 30));
        mTransfer.setPreferredSize(new Dimension(150, 30));
        mBase.setPreferredSize(new Dimension(150, 30));
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
        //lPatient.setHorizontalAlignment(SwingConstants.RIGHT);
        tTimeCall.setHorizontalAlignment(JTextField.CENTER);
        tTimeDeparture.setHorizontalAlignment(JTextField.CENTER);
        tTimeArrival.setHorizontalAlignment(JTextField.CENTER);
        tTimeTransfer.setHorizontalAlignment(JTextField.CENTER);
        tTimeHospital.setHorizontalAlignment(JTextField.CENTER);
        tTimeComeback.setHorizontalAlignment(JTextField.CENTER);
        mResultado.setHorizontalAlignment(SwingConstants.CENTER);
        mResultado.setHorizontalTextPosition(JTextField.CENTER);
        iFalseAlarm.setHorizontalAlignment(JTextField.CENTER);
        lTimeCall.setHorizontalAlignment(JTextField.RIGHT);
        lTimeDeparture.setHorizontalAlignment(JTextField.RIGHT);
        lTimeArrival.setHorizontalAlignment(JTextField.RIGHT);
        lTimeTransfer.setHorizontalAlignment(JTextField.RIGHT);
        lTimeHospital.setHorizontalAlignment(JTextField.RIGHT);
        lTimeComeback.setHorizontalAlignment(JTextField.RIGHT);
        tTimeDeparture.setText("00:00:00");
        tTimeArrival.setText("00:00:00");
        tTimeTransfer.setText("00:00:00");
        tTimeHospital.setText("00:00:00");
        tTimeComeback.setText("00:00:00");
        tNote.setLineWrap(true);
        tNote.setWrapStyleWord(true);
        tTimeCall.setText(sTimeCall);

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
        //this.add(pDir, BorderLayout.LINE_START );
        this.add(pDir);

        pTransfer.add(lRadioOper);
        pTransfer.add(mBarRadioOper);
        pTransfer.add(lApplicant);
        pTransfer.add(tApplicant);
        pTransfer.add(lSpace0);
        pTransfer.add(mBarResultado);
        pTransfer.add(mBarPriorityTransfer);
        pTransfer.add(mBarTransfer);
        pTransfer.add(tTransfer);
        this.add(pTransfer);
//        this.add(lSpace1);

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
        this.add(lSpace3);

        this.add(lAmbulance);
        this.add(mBarAmbulance);
        this.add(lKmDeparture);
        this.add(tKmDeparture);
        //this.add(lKmComeback);
        //this.add(tKmComeback);
        this.add(mBarBase);
        this.add(lFolio);
        this.add(tFolio);
        this.add(lOper);
        this.add(mBarOper);
        this.add(lParamedic);
        this.add(mBarParamedic);
        this.add(lSpace9);
        this.add(lOperVoluntary);
        this.add(tOperVoluntary);
        this.add(lParamedicVoluntary);
        this.add(tParamedicVoluntary);
        this.add(lNote);
        this.add(scroll, BorderLayout.CENTER);
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
        this.add(pPatient);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("event: " + e.getActionCommand() + " \t summoner: " + e.getSource().getClass().getSimpleName());
        char[] cadena = e.getActionCommand().toCharArray();
        String command = "";
        if (e.getActionCommand().length() > 1) {
            command = "" + cadena[0] + cadena[1];
        }
        Calendar calendario = new GregorianCalendar();
        if (e.getSource().getClass().getSimpleName().equals("JRadioButton")) {
            switch (e.getActionCommand().toCharArray()[0]) {
                case 'M':
                    if (e.getActionCommand().toCharArray()[1] == 'a') {
                        sex = e.getActionCommand();
                    } else {
                        status = e.getActionCommand();
                    }
                    break;
                case 'F':
                    sex = e.getActionCommand();
                    break;
                case 'V':
                    status = e.getActionCommand();
                    break;
                default:
                    System.out.println("El event JRadioButton es: " + e.getActionCommand());
                    break;
            }
        } else if (e.getSource().getClass().getSimpleName().equals("JMenuItem")) {
            if (e.getActionCommand().toCharArray()[0] == 'P' && e.getActionCommand().toCharArray()[1] == 'r') {
                mPriorityTransfer.setText(e.getActionCommand());
            } else if (cadena[2] == '#') {
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
            } else {
                switch (e.getActionCommand()) {
                    case "Falsa alarma":
                    case "No encontrado":
                    case "Tranf por tercero":
                    case "No necesita tranf":
                    case "No quiere tranf":
                    case "Fallecido":
                    case "Traslado":
                        mResultado.setText(e.getActionCommand());
                        break;
                    case "Centro":
                    case "Norte":
                    case "Boca":
                        mBase.setText(e.getActionCommand());
                        break;
                    default:
                        mTransfer.setText(e.getActionCommand());
                }
            }
        } else if (e.getActionCommand().equals(" + ")) {
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
            if(!tNumFrap.getText().equals("")&&tNumFrap.getText() != null){
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
        } else {
            bTime.setPreferredSize(new Dimension(windowX / 13, 30));
            switch (classTime) {
                case 1:
                    timeDeparture = timeFull.format(calendario.getTime());
                    tTimeDeparture.setText(time.format(calendario.getTime()));
                    bTime.setText("Hora llegada");
                    classTime++;
                    break;
                case 2:
                    timeArrival = timeFull.format(calendario.getTime());
                    tTimeArrival.setText(time.format(calendario.getTime()));
                    bTime.setText("Hora traslado");
                    classTime++;
                    break;
                case 3:
                    if (mResultado.getText().equals("Traslado")) {
                        timeTransfer = timeFull.format(calendario.getTime());
                        tTimeTransfer.setText(time.format(calendario.getTime()));
                        bTime.setText("Hora hospital");
                        classTime++;
                        if (mTransfer.getText().equals("CRUZ ROJA")) {
                            bTime.setText("Hora base");
                            classTime++;
                            timeHospital = "2000-1-1 0:00:00";
                        }
                    } else {
                        timeTransfer = timeFull.format(calendario.getTime());
                        tTimeTransfer.setText(time.format(calendario.getTime()));
                        bTime.setText("Hora base");
                        classTime += 2;
                        timeHospital = "2000-1-1 0:00:00";
                    }
                    break;
                case 4:
                    timeHospital = timeFull.format(calendario.getTime());
                    tTimeHospital.setText(time.format(calendario.getTime()));
                    bTime.setText("Hora base");
                    classTime++;
                    break;
                case 5:
                    timeComeback = timeFull.format(calendario.getTime());
                    tTimeComeback.setText(time.format(calendario.getTime()));
                    bTime.setText("Hora");
                    int obstetricoMonthes = 0;
                    if (!tab.tObstetricoMonthes.getText().equals("") && tab.tObstetricoMonthes.getText() != null) {
                        obstetricoMonthes = Integer.valueOf(tab.tObstetricoMonthes.getText());
                    }
                    String sPatient = "";
                    String obstetrico = "";
                    String trauma = "";
                    int idEmergency = 0;
                    int kmTraveled = 0;
                    int alive = 0;
                    if (tAlive.getText().equals("") || tAlive.getText() == null) {
                        if (multSingle.isSelected()) {
                            alive = 1;
                        }
                    } else {
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
                    JPanel pKm = new JPanel();
                    JTextField tKm = new JTextField(8);
                    pKm.add(new JLabel("Km:"));
                    pKm.add(tKm);
                    int kmAmbulance = 0;
                    do {
                        if (0 == JOptionPane.showConfirmDialog(null, pKm, "Km", JOptionPane.DEFAULT_OPTION)) {
                            try {
                                kmAmbulance = Integer.parseInt(tKm.getText());
                            } catch (NumberFormatException ex) {
                                tKm.setText("");
                                kmAmbulance = 0;
                                JOptionPane.showMessageDialog(null, "No es un número\n" + ex, "ERROR", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Debe introducir los Km de ambulancia al llegar a la base",
                                    "ERROR", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (kmAmbulance == 0);
                    kmTraveled = kmAmbulance - Integer.valueOf(tKmDeparture.getText());
                    String errorKm = db.insertAmbulanceKm(idAmbulance, kmAmbulance);
                    if (!errorKm.equals("done")) {
                        JOptionPane.showMessageDialog(null, errorKm, "ERROR", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Km recorridas: " + kmTraveled, "", JOptionPane.INFORMATION_MESSAGE);
                    }
                    String em = db.insertEmergency(tDir.getText(), tEntre.getText(), tRef.getText(),
                            tCol.getText(), tDel.getText(), tApplicant.getText(), mResultado.getText(),
                            transfer, priority, alive, deads, idParamedic, idOper,
                            idRadioOper, idAmbulance, mBase.getText(), tOperVoluntary.getText(), tParamedicVoluntary.getText(),
                            timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital, timeComeback, tNote.getText(),
                            tipeCallmain, callmain, kmTraveled);
                    System.out.println("insert= " + em);
                    if (em.toCharArray()[0] == 'E' && em.toCharArray()[1] == 'M') {
                        String word = "";
                        cadena = em.toCharArray();
                        for (int i = 3; i < cadena.length; i++) {
                            word += cadena[i];
                        }
                        idEmergency = Integer.valueOf(word);
                        for (int m = 0; m < patient.size(); m++) {
                            if (!tab.mObstetrico.getText().equals("Tipo de obstetrico")) {
                                obstetrico = tab.mObstetrico.getText();
                            }
                            if (!tab.mTrauma.getText().equals("Tipo de Trauma")) {
                                trauma = tab.mTrauma.getText();
                            }                            
                            sPatient = db.insertPatient(patient.get(m)[0], patient.get(m)[1],
                                    patient.get(m)[2], Integer.valueOf(patient.get(m)[3]), patient.get(m)[4],
                                    patient.get(m)[5], patient.get(m)[6], idEmergency, trauma,
                                    tab.tMotivo.getText(), tab.tPadecimiento.getText(), tab.tMedicamento.getText(),
                                    tab.tEventoPrevio.getText(), obstetrico, obstetricoMonthes);
                        }
                        System.out.println("id Paciente: " + sPatient);
                    } else {
                        System.out.println("ctrData/ActionPerformed: error# " + em);
                    }
                    archivo a = new archivo();
                    String[][] data = new String[31][2];
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
                    data[24][0] = "sex";
                    data[25][0] = "age";
                    data[26][0] = "transfer";
                    data[27][0] = "denominacion";
                    data[28][0] = "note";
                    data[29][0] = "telephone";
                    data[30][0] = "radiooper";
                    data[0][1] = date.format(calendario.getTime());
                    data[1][1] = tFolio.getText();
                    data[2][1] = tDir.getText();
                    data[3][1] = tEntre.getText();
                    data[4][1] = tCol.getText();
                    data[5][1] = tDel.getText();
                    data[6][1] = tipeCallmain + " " + callmain;
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
                    data[11][1] = db.divTimeFull(timeCall, true);
                    data[12][1] = db.divTimeFull(timeDeparture, true);
                    data[13][1] = db.divTimeFull(timeArrival, true);
                    data[14][1] = db.divTimeFull(timeTransfer, true);
                    data[15][1] = db.divTimeFull(timeHospital, true);
                    data[16][1] = db.divTimeFull(timeComeback, true);
                    data[17][1] = db.subTime(data[11][1], data[12][1]);
                    data[18][1] = db.subTime(data[12][1], data[13][1]);
                    data[19][1] = db.subTime(data[13][1], data[14][1]);
                    if (data[15][1].equals("0:00:00")) {
                        data[20][1] = "";
                        data[21][1] = db.subTime(data[14][1], data[16][1]);
                    } else {
                        data[20][1] = db.subTime(data[14][1], data[15][1]);
                        data[21][1] = db.subTime(data[15][1], data[16][1]);
                    }
                    data[22][1] = tAlive.getText();
                    data[23][1] = tDeads.getText();
                    data[24][1] = sex;
                    data[25][1] = tAgeOld.getText();
                    data[26][1] = transfer;
                    data[27][1] = "";
                    data[28][1] = tNote.getText();
                    data[29][1] = "";
                    data[30][1] = nameRadioOper;

                    a.replaceWordData("resource/formatoCtrlAmb.docx", "C:/CtrlAmb/Emergencia#" + idEmergency + ".docx", data);
                    try {
                        Runtime.getRuntime().exec("cmd /c start C:\\CtrlAmb\\Emergencia#" + idEmergency + ".docx");
                    } catch (IOException ex) {
                        Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    bTime.setEnabled(false);
                    emergency.dispose();
                    break;
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
                mAmbulance.add(iEmployees);
                iEmployees.addActionListener(this);
                word = "";
            } else {
                word += cadena[i];
            }
        }
    }
}
