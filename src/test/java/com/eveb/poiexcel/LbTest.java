package com.eveb.poiexcel;

import lombok.Data;


@Data
public class LbTest {
    private String name;
    private Double yxtz;
    private Double pc;
    private Double dl;
    private Double bbinTz;
    private Double bbinPc;
    private Double bbinDl;
    private Double ce;

    @Override
    public String toString() {
        return "LbTest{" +
                "name='" + name + '\'' +
                ", yxtz=" + yxtz +
                ", pc=" + pc +
                ", dl=" + dl +
                ", bbinTz=" + bbinTz +
                ", bbinPc=" + bbinPc +
                ", bbinDl=" + bbinDl +
                ", ce=" + ce +
                '}';
    }

    public LbTest(String name, Double yxtz, Double pc, Double dl, Double bbinTz, Double bbinPc, Double bbinDl, Double ce) {
        this.name = name;
        this.yxtz = yxtz;
        this.pc = pc;
        this.dl = dl;
        this.bbinTz = bbinTz;
        this.bbinPc = bbinPc;
        this.bbinDl = bbinDl;
        this.ce = ce;
    }

    public LbTest() {
    }
}
