

import java.time.LocalTime;
import java.util.ArrayList;

public class Dia {
    static private final ArrayList <String> horarios = new ArrayList<>();
    static private final ArrayList <LocalTime> extras = new ArrayList<>();
    static private final ArrayList <LocalTime> atrasos = new ArrayList<>();
    static private String se = "";
    static private String sa = "";
    static private int totalhe, totalme, totalma, totalha;
    private LocalTime ext = LocalTime.of(0,0) ;


    public Dia(String horario) {
        String horario1 = horario.replaceAll(" ", "");
        horarios.add(horario1);
    }

    public String calcularMes(){
        for (int i = 0; i < horarios.size();i++){
            LocalTime atraso;
            LocalTime extra;
            if (horarios.get(i).replaceAll(" ","").length() < 20){

                int he = setHe(i);
                int me = setMe(i);
                int hs = setHs(i);
                int ms = setMs(i);

                LocalTime horaSaidaCalc = LocalTime.of(hs,ms);
                LocalTime calc = horaSaidaCalc.minusHours(he).minusMinutes(me);
                LocalTime tempoSabado = LocalTime.of(4,0);

                if(calc.getHour() < 4){
                    atraso = tempoSabado.minusHours(calc.getHour())
                            .minusMinutes(calc.getMinute());
                    atrasos.add(atraso);
                } else if (calc.getHour() > 4 || calc.getMinute() > 0){
                    extra = calc.minusHours(tempoSabado.getHour())
                            .minusMinutes(tempoSabado.getMinute());
                    extras.add(extra);
                }

            }else{
                int he = setHe(i);
                int me = setMe(i);
                int hs = setHs(i);
                int ms = setMs(i);
                int hae = setHae(i);
                int mae = setMae(i);
                int has = setHas(i);
                int mas = setMas(i);

                LocalTime horaSaidaCalc = LocalTime.of(hs,ms);
                LocalTime calcTrabalho = horaSaidaCalc.minusHours(he)
                        .minusMinutes(me);

                LocalTime horaEntradaAlmocoCalc = LocalTime.of(hae,mae);
                LocalTime calcAlmoco = horaEntradaAlmocoCalc.minusHours(has)
                        .minusMinutes(mas);

                LocalTime tempoSemana = LocalTime.of(8,0);
                LocalTime calcTotal = calcTrabalho.minusHours(calcAlmoco.getHour())
                        .minusMinutes(calcAlmoco.getMinute());


                if(calcTotal.getHour() < 8){
                    atraso = tempoSemana.minusHours(calcTotal.getHour())
                            .minusMinutes(calcTotal.getMinute());
                    atrasos.add(atraso);
                } else if (calcTotal.getHour() > 8 || calcTotal.getMinute()>0) {
                    extra = calcTotal.minusHours(tempoSemana.getHour())
                            .minusMinutes(tempoSemana.getMinute());
                    extras.add(extra);
                }
            }
        }
        for (int i = 0; i < extras.size(); i++) {
            int h = extras.get(i).getHour();
            int m = extras.get(i).getMinute();
            totalhe += h;
            totalme += m;


        }
        for (int i = 0; i < atrasos.size(); i++) {
            int h = atrasos.get(i).getHour();
            int m = atrasos.get(i).getMinute();
            totalha += h;
            totalma += m;

        }
        sa = String.valueOf(ext.plusHours(totalha).plusMinutes(totalma));
        se = String.valueOf(ext.plusHours(totalhe).plusMinutes(totalme));
        return "extras "+ extras + " atrasos "+ atrasos + " total extra " + se
                + " total atraso " + sa;
        // 09:1013:00
        // 0123456789     0-2 3-5 5-7 8-10
        //09:1018:0013:0014:00 0-2 he 3-5 ms 10-12 hae 13-15 mae 15-17 has 18-20 mas
    }
    private int setHe(int i){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(0,2));
    }
    private int setMe(int i){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(3,5));
    }
    private int setHs(int i){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(5,7));
    }
    private int setMs(int i ){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(8,10));
    }
    private int setHae(int i ){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(10,12));
    }
    private int setMae(int i ){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(13,15));
    }
    private int setHas(int i ){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(15,17));
    }
    private int setMas(int i ){
        return Integer.parseInt(horarios.get(i)
                .replaceAll(" ","").substring(18,20));
    }
}

