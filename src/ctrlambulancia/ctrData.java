/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrlambulancia;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Sistemas
 */
public class ctrData extends JPanel implements ActionListener {

    JFrame emergency;
    tabData tab;
    SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat timeFull = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    ConxDB db;
    int classTime = 1;
    JTextField tDir = new JTextField(30);
    JTextField tEntre = new JTextField(20);
    JTextField tRef = new JTextField(20);
    JTextField tCol = new JTextField(20);
    JTextField tDel = new JTextField(20);
    JTextField tRadioOper = new JTextField(20);
    JTextField tApplicant = new JTextField(20);
    JTextField tNumPatient = new JTextField(5);
    JTextField tAlive = new JTextField(5);
    JTextField tDeads = new JTextField(5);
    JTextField tAgeOld = new JTextField(5);
    JTextField tNumFrap = new JTextField(5);
    JTextField tNamePatient = new JTextField(20);
    JTextField tLastNamePatient = new JTextField(15);
    JTextField tLastName2Patient = new JTextField(15);
    JTextField tAmbulance = new JTextField(5);
    JTextField tKmDeparture = new JTextField(5);
    JTextField tKmComeback = new JTextField(5);
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
    JRadioButton sexM = new JRadioButton("M", false);
    JRadioButton sexF = new JRadioButton("F", false);
    JButton bTime = new JButton("Hora salida");
    JMenu mResultado = new JMenu("Resultado");
    JMenu mPriorityTransfer = new JMenu("Prioridad del traslado");
    JMenu mTransfer = new JMenu("Hospital a transferir");
    JMenu mOper = new JMenu();
    JMenu mRadioOper = new JMenu();
    JMenu mParamedic = new JMenu();
    JMenu mAmbulance = new JMenu();
    ButtonGroup groupMult = new ButtonGroup();
    ButtonGroup groupSex = new ButtonGroup();
    JTextArea tNote = new JTextArea(5, 30);
    int windowX = 0;
    int idAmbulance = 0;
    int idParamedic = 0;
    int idOper = 0;
    int idRadioOper = 0;
    int idPatient = 0;
    String sTimeCall;
    String sex = "";
    String timeCall = "";
    String timeDeparture = "";
    String timeArrival = "";
    String timeTransfer = "";
    String timeHospital = "";
    String timeComeback = "";
    char[] cadena;

    public int getWindowX() {
        return windowX;
    }

    public void setWindowX(int windowX) {
        this.windowX = windowX;
        //bTime.setPreferredSize(new Dimension(windowX / 13, 30));
        System.out.println(windowX + "\treWind");
        //ctrData(sTimeCall, windowX);
    }

    public ctrData(String sTimeCall, int windowX, JFrame emergency, tabData tab, ConxDB db, String sAmbulance,
            String sKmAmbulance, String sOperVoluntary, String sParamedicVoluntary, String sOper, String sParamedic,
            String sRadioOper, String sApplicant) {
        this.timeCall = sTimeCall;
        this.windowX = windowX;
        this.emergency = emergency;
        this.tab = tab;
        this.db = db;
        mAmbulance.setText(sAmbulance);
        tKmDeparture.setText(sKmAmbulance);
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
        cadena = sAmbulance.toCharArray();
        if (cadena[0] == 'A' && cadena[1] == 'B') {
            for (int i = 3; i < cadena.length; i++) {
                if (cadena[i] == ' ') {
                    this.idAmbulance = Integer.valueOf(word);
                    i = cadena.length;
                } else {
                    word += cadena[i];
                }
            }
        }
        word = "";
        cadena = sParamedic.toCharArray();
        if (cadena[0] == 'P' && cadena[1] == 'M') {
            for (int i = 3; i < cadena.length; i++) {
                if (cadena[i] == ' ') {
                    this.idParamedic = Integer.valueOf(word);
                    i = cadena.length;
                } else {
                    word += cadena[i];
                }
            }
        }
        word = "";
        cadena = sOper.toCharArray();
        if (cadena[0] == 'O' && cadena[1] == 'P') {
            for (int i = 3; i < cadena.length; i++) {
                if (cadena[i] == ' ') {
                    this.idOper = Integer.valueOf(word);
                    i = cadena.length;
                } else {
                    word += cadena[i];
                }
            }
        }
        word = "";
        cadena = sRadioOper.toCharArray();
        if (cadena[0] == 'R' && cadena[1] == 'P') {
            for (int i = 3; i < cadena.length; i++) {
                if (cadena[i] == ' ') {
                    this.idRadioOper = Integer.valueOf(word);
                    i = cadena.length;
                } else {
                    word += cadena[i];
                }
            }
        }

        JLabel lSpace0 = new JLabel("");
        JLabel lSpace1 = new JLabel("");
        JLabel lSpace2 = new JLabel("");
        JLabel lSpace3 = new JLabel("");
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
        JLabel lPatient = new JLabel("Pacientes:");
        JLabel lSex = new JLabel("Sexo:");
        JLabel lAgeOld = new JLabel("Edad:");
        JLabel lAlive = new JLabel("Vivos:");
        JLabel lDeads = new JLabel("Muertos:");
        JLabel lNumFrap = new JLabel("Num frap:");
        JLabel lNamePatient = new JLabel("Nombre:");
        JLabel lLastNamePatient = new JLabel("Apellido paterno:");
        JLabel lLastName2Patient = new JLabel("Apellido materno:");
        JLabel lNumPatient = new JLabel("Numero de paciente CRM:");
        JLabel lAmbulance = new JLabel("Ambulancia:");
        JLabel lKmDeparture = new JLabel("Km salida:");
        //JLabel lKmComeback = new JLabel("Km llegada:");
        JLabel lOperVoluntary = new JLabel("Operador voluntario:");
        JLabel lParamedicVoluntary = new JLabel("Paramedico voluntario:");
        JLabel lOper = new JLabel("Operador:");
        JLabel lParamedic = new JLabel("Paramedico:");
        JLabel lNote = new JLabel("Observacion:");
        //JTextField tNote = new JTextField(50);
        JMenuBar mBarResultado = new JMenuBar();
        JMenuBar mBarPriorityTransfer = new JMenuBar();
        JMenuBar mBarTransfer = new JMenuBar();
        JMenuBar mBarOper = new JMenuBar();
        JMenuBar mBarRadioOper = new JMenuBar();
        JMenuBar mBarParamedic = new JMenuBar();
        JMenuBar mBarAmbulance = new JMenuBar();
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
        multSingle.addActionListener(this);
        multGroup.addActionListener(this);
        sexM.addActionListener(this);
        sexF.addActionListener(this);
        bTime.addActionListener(this);
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
        groupMult.add(multSingle);
        groupMult.add(multGroup);
        groupSex.add(sexM);
        groupSex.add(sexF);
        menuUpdate();
        mBarOper.add(mOper);
        mBarRadioOper.add(mRadioOper);
        mBarParamedic.add(mParamedic);
        mBarAmbulance.add(mAmbulance);
        mOper.setPreferredSize(new Dimension(250, 20));
        mRadioOper.setPreferredSize(new Dimension(250, 20));
        mParamedic.setPreferredSize(new Dimension(250, 20));
        mAmbulance.setPreferredSize(new Dimension(150, 20));
        tKmDeparture.setEditable(false);

        lDir.setPreferredSize(new Dimension(60, 50));
        //lRadioOper.setPreferredSize(new Dimension(120, 50));
        lAlive.setPreferredSize(new Dimension(60, 50));
        lDeads.setPreferredSize(new Dimension(60, 50));
        lLastNamePatient.setPreferredSize(new Dimension(120, 50));
        lLastName2Patient.setPreferredSize(new Dimension(120, 50));
        lSpace0.setPreferredSize(new Dimension(25, 50));
        lSpace1.setPreferredSize(new Dimension(25, 50));
        lSpace2.setPreferredSize(new Dimension(200, 50));
        lSpace3.setPreferredSize(new Dimension(200, 50));
        lSex.setPreferredSize(new Dimension(50, 50));
        lAgeOld.setPreferredSize(new Dimension(50, 50));
        lNamePatient.setPreferredSize(new Dimension(70, 50));
        lNumPatient.setPreferredSize(new Dimension(170, 50));
        lAmbulance.setPreferredSize(new Dimension(170, 50));
        lKmDeparture.setPreferredSize(new Dimension(70, 50));
        lOper.setPreferredSize(new Dimension(120, 50));
        lParamedic.setPreferredSize(new Dimension(120, 50));
        lOperVoluntary.setPreferredSize(new Dimension(170, 50));
        lParamedicVoluntary.setPreferredSize(new Dimension(170, 50));
        lNote.setPreferredSize(new Dimension(100, 50));
        lDir.setHorizontalAlignment(SwingConstants.RIGHT);
        lRadioOper.setHorizontalAlignment(SwingConstants.RIGHT);
        lAlive.setHorizontalAlignment(SwingConstants.RIGHT);
        lDeads.setHorizontalAlignment(SwingConstants.RIGHT);
        lLastNamePatient.setHorizontalAlignment(SwingConstants.RIGHT);
        lLastName2Patient.setHorizontalAlignment(SwingConstants.RIGHT);
        lSex.setHorizontalAlignment(SwingConstants.RIGHT);
        lAgeOld.setHorizontalAlignment(SwingConstants.RIGHT);
        lNamePatient.setHorizontalAlignment(SwingConstants.RIGHT);
        lNumPatient.setHorizontalAlignment(SwingConstants.RIGHT);
        lAmbulance.setHorizontalAlignment(SwingConstants.RIGHT);
        lKmDeparture.setHorizontalAlignment(SwingConstants.RIGHT);
        lOper.setHorizontalAlignment(SwingConstants.RIGHT);
        lParamedic.setHorizontalAlignment(SwingConstants.RIGHT);
        lOperVoluntary.setHorizontalAlignment(SwingConstants.RIGHT);
        lParamedicVoluntary.setHorizontalAlignment(SwingConstants.RIGHT);
        lNote.setHorizontalAlignment(SwingConstants.RIGHT);
        //lPatient.setHorizontalAlignment(SwingConstants.RIGHT);
        multSingle.setEnabled(false);
        multGroup.setEnabled(false);
        sexM.setEnabled(false);
        sexF.setEnabled(false);
        tTimeCall.setText(sTimeCall);
        tTimeDeparture.setText("00:00:00");
        tTimeArrival.setText("00:00:00");
        tTimeTransfer.setText("00:00:00");
        tTimeHospital.setText("00:00:00");
        tTimeComeback.setText("00:00:00");
        tTimeCall.setEditable(false);
        tTimeDeparture.setEditable(false);
        tTimeArrival.setEditable(false);
        tTimeTransfer.setEditable(false);
        tTimeHospital.setEditable(false);
        tTimeComeback.setEditable(false);
        tTimeCall.setHorizontalAlignment(JTextField.CENTER);
        tTimeDeparture.setHorizontalAlignment(JTextField.CENTER);
        tTimeArrival.setHorizontalAlignment(JTextField.CENTER);
        tTimeTransfer.setHorizontalAlignment(JTextField.CENTER);
        tTimeHospital.setHorizontalAlignment(JTextField.CENTER);
        tTimeComeback.setHorizontalAlignment(JTextField.CENTER);
        mPriorityTransfer.setEnabled(false);
        mTransfer.setEnabled(false);
        tAlive.setEditable(false);
        tDeads.setEditable(false);
        tAgeOld.setEditable(false);
        tNumFrap.setEditable(false);
        tNamePatient.setEditable(false);
        tLastNamePatient.setEditable(false);
        tLastName2Patient.setEditable(false);
        bTime.setPreferredSize(new Dimension(windowX / 13, 30));
        mResultado.setPreferredSize(new Dimension(150, 30));
        mPriorityTransfer.setPreferredSize(new Dimension(150, 30));
        mTransfer.setPreferredSize(new Dimension(150, 30));
        mResultado.setHorizontalAlignment(SwingConstants.CENTER);
        mResultado.setHorizontalTextPosition(JTextField.CENTER);
        iFalseAlarm.setHorizontalAlignment(JTextField.CENTER);

        this.setLayout(new FlowLayout());
        //this.setLayout(new BorderLayout());
        //pPatient.setLayout(new FlowLayout()); 
        //pDir.setLayout(new FlowLayout());
        //pDir.setLayout(new BorderLayout());

        lTimeCall.setHorizontalAlignment(JTextField.RIGHT);
        lTimeDeparture.setHorizontalAlignment(JTextField.RIGHT);
        lTimeArrival.setHorizontalAlignment(JTextField.RIGHT);
        lTimeTransfer.setHorizontalAlignment(JTextField.RIGHT);
        lTimeHospital.setHorizontalAlignment(JTextField.RIGHT);
        lTimeComeback.setHorizontalAlignment(JTextField.RIGHT);

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
        this.add(pTransfer);
        this.add(lSpace1);
        this.add(multSingle);
        this.add(multGroup);
        this.add(lAlive);
        this.add(tAlive);
        this.add(lDeads);
        this.add(tDeads);
        //this.add(lSpace2);
        this.add(lPatient);
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
        //this.add(lNumPatient);
        //this.add(tNumPatient);
        this.add(lSpace2);
        this.add(lAmbulance);
        this.add(mBarAmbulance);
        this.add(lKmDeparture);
        this.add(tKmDeparture);
        //this.add(lKmComeback);
        //this.add(tKmComeback);
        this.add(lOper);
        this.add(mBarOper);
        this.add(lParamedic);
        this.add(mBarParamedic);
        this.add(lSpace3);
        this.add(lOperVoluntary);
        this.add(tOperVoluntary);
        this.add(lParamedicVoluntary);
        this.add(tParamedicVoluntary);
        this.add(lNote);
        this.add(tNote);
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
                    System.out.println("El sexo es: " + e.getActionCommand());
                    sex = e.getActionCommand();
                    break;
                case 'F':
                    System.out.println("El sexo es: " + e.getActionCommand());
                    sex = e.getActionCommand();
                    break;
                case 'G':
                    tAgeOld.setText(null);
                    tNumFrap.setText(null);
                    tNamePatient.setText(null);
                    tLastNamePatient.setText(null);
                    tLastName2Patient.setText(null);
                    groupSex.clearSelection();
                    sexM.setEnabled(false);
                    sexF.setEnabled(false);
                    tAlive.setEditable(true);
                    tDeads.setEditable(true);
                    tAgeOld.setEditable(false);
                    tNumFrap.setEditable(false);
                    tNamePatient.setEditable(false);
                    tLastNamePatient.setEditable(false);
                    tLastName2Patient.setEditable(false);
                    System.out.println("single: " + multSingle.isSelected());
                    break;
                case 'I':
                    tAlive.setText(null);
                    tDeads.setText(null);
                    sexM.setEnabled(true);
                    sexF.setEnabled(true);
                    tAlive.setEditable(false);
                    tDeads.setEditable(false);
                    tAgeOld.setEditable(true);
                    tNumFrap.setEditable(true);
                    tNamePatient.setEditable(true);
                    tLastNamePatient.setEditable(true);
                    tLastName2Patient.setEditable(true);
                    System.out.println("single: " + multSingle.isSelected());
                    break;
                default:
                    System.out.println("El event JRadioButton es: " + e.getActionCommand());
                    break;
            }
        } else {
            if (e.getSource().getClass().getSimpleName().equals("JMenuItem")) {
                if (e.getActionCommand().toCharArray()[0] == 'P' && e.getActionCommand().toCharArray()[1] == 'r') {
                    mPriorityTransfer.setText(e.getActionCommand());
                } else if (e.getActionCommand().equals("Falsa alarma")
                        || e.getActionCommand().equals("No encontrado")
                        || e.getActionCommand().equals("Tranf por tercero")
                        || e.getActionCommand().equals("No necesita tranf")
                        || e.getActionCommand().equals("No quiere tranf")
                        || e.getActionCommand().equals("Fallecido")) {
                    mResultado.setText(e.getActionCommand());
                    mPriorityTransfer.setEnabled(false);
                    mTransfer.setEnabled(false);
                    tAlive.setText(null);
                    tDeads.setText(null);
                    tAgeOld.setText(null);
                    tNumFrap.setText(null);
                    tNamePatient.setText(null);
                    tLastNamePatient.setText(null);
                    tLastName2Patient.setText(null);
                    groupMult.clearSelection();
                    groupSex.clearSelection();
                    multSingle.setEnabled(false);
                    multGroup.setEnabled(false);
                    sexM.setEnabled(false);
                    sexF.setEnabled(false);
                    tAlive.setEditable(false);
                    tDeads.setEditable(false);
                    tAgeOld.setEditable(false);
                    tNumFrap.setEditable(false);
                    tNamePatient.setEditable(false);
                    tLastNamePatient.setEditable(false);
                    tLastName2Patient.setEditable(false);
                } else if (e.getActionCommand().equals("Traslado")) {
                    mResultado.setText(e.getActionCommand());
                    mPriorityTransfer.setEnabled(true);
                    mTransfer.setEnabled(true);
                    multSingle.setEnabled(true);
                    multGroup.setEnabled(true);

                } else if (cadena[2] == '#') {
                    switch (command) {
                        case "AB":
                            String word = "";
                            for (int i = 3; i < cadena.length; i++) {
                                if (cadena[i] == ' ') {
                                    tKmDeparture.setText(String.valueOf(db.consultAmbulanceKm(Integer.valueOf(word))));
                                    i = cadena.length;
                                } else {
                                    word += cadena[i];
                                }
                            }
                            mAmbulance.setText(e.getActionCommand());
                            break;
                        case "OP":
                            mOper.setText(e.getActionCommand());
                            break;
                        case "RO":
                            mRadioOper.setText(e.getActionCommand());
                            break;
                        case "PM":
                            mParamedic.setText(e.getActionCommand());
                            break;
                    }
                } else {
                    mTransfer.setText(e.getActionCommand());
                }
            } else {
                bTime.setPreferredSize(new Dimension(windowX / 13, 30));
                switch (classTime) {
                    case 1:
                        if (tDir.getText().equals("") || tCol.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "La dirección y colonia son requisitos para que la ambulancia pueda salir");
                        } else {
                            timeDeparture = timeFull.format(calendario.getTime());
                            tTimeDeparture.setText(time.format(calendario.getTime()));
                            bTime.setText("Hora llegada");
                            classTime++;
                        }
                        break;
                    case 2:
                        timeArrival = timeFull.format(calendario.getTime());
                        tTimeArrival.setText(time.format(calendario.getTime()));
                        bTime.setText("Hora traslado");
                        classTime++;
                        break;
                    case 3:
                        if (mResultado.getText().equals("Resultado")) {
                            JOptionPane.showMessageDialog(null, "Se debe aclarar el resultado antes de poder retirarce del lugar");
                        } else {
                            if (mResultado.getText().equals("Traslado")) {
                                if (mPriorityTransfer.getText().equals("Prioridad del traslado")
                                        || mTransfer.getText().equals("Transferidos")) {
                                    JOptionPane.showMessageDialog(null, "Se debe asignar una prioridad "
                                            + "y el hospital donde será transferido antes de poder retirarce del lugar");
                                } else {
                                    timeTransfer = timeFull.format(calendario.getTime());
                                    tTimeTransfer.setText(time.format(calendario.getTime()));
                                    bTime.setText("Hora hospital");
                                    classTime++;
                                }
                            } else {
                                timeTransfer = timeFull.format(calendario.getTime());
                                tTimeTransfer.setText(time.format(calendario.getTime()));
                                bTime.setText("Hora base");
                                classTime += 2;
                            }
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
                        bTime.setEnabled(false);
                        emergency.dispose();
                        int obstetricoMonthes = 0;
                        if (!tab.tObstetricoMonthes.getText().equals("")) {
                            obstetricoMonthes = Integer.valueOf(tab.tObstetricoMonthes.getText());
                        }
                        String sPatient = db.insertPatient(tNamePatient.getText(), tLastNamePatient.getText(),
                                tLastName2Patient.getText(), Integer.valueOf(tAgeOld.getText()), sex,
                                tab.mTrauma.getText(), tab.tMotivo.getText(), tab.tPadecimiento.getText(),
                                tab.tMedicamento.getText(), tab.tEventoPrevio.getText(), tab.mObstetrico.getText(),
                                obstetricoMonthes);
                        System.out.println("id Paciente: " + sPatient);
                        String word = "";
                        cadena = sPatient.toCharArray();
                        if (cadena[0] == 'P' && cadena[1] == 'C') {
                            for (int i = 3; i < cadena.length; i++) {
                                word += cadena[i];
                            }
                            idPatient = Integer.valueOf(word);
                        }
                        int alive = 0;
                        if (tAlive.getText().equals("")) {
                            if (multSingle.isSelected()) {
                                alive = 1;
                            }
                        } else {
                            alive = Integer.valueOf(tAlive.getText());
                        }
                        int deads = 0;
                        if (!tDeads.getText().equals("")) {
                            deads = Integer.valueOf(tDeads.getText());
                        }
                        int priority = 0;
                        if (!mPriorityTransfer.getText().equals("Prioridad del traslado")) {
                            priority = Integer.valueOf("" + mPriorityTransfer.getText().toCharArray()[10]);
                        }
                        String s = db.insertEmergency(tDir.getText(), tEntre.getText(), tRef.getText(),
                                tCol.getText(), tDel.getText(), tApplicant.getText(), mResultado.getText(),
                                mTransfer.getText(), priority, alive, deads, idPatient, idParamedic, idOper,
                                idAmbulance, idRadioOper, tOperVoluntary.getText(), tParamedicVoluntary.getText(),
                                timeCall, timeDeparture, timeArrival, timeTransfer, timeHospital, timeComeback, tNote.getText());
                        System.out.println("Registro Emergency: " + s);
                        break;
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
