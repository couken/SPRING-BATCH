package jp.co.tokyo_gas.batch.json.writer;


import jp.co.tokyo_gas.batch.common.CommonProcessor;
import jp.co.tokyo_gas.batch.entity.CafeCat;
import jp.co.tokyo_gas.batch.entity.Cat;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

/**
 * @author shuliangzhao
 * @Title: CatProcessor
 * @ProjectName spring-boot-learn
 * @Description: TODO
 * @date 2019/9/10 20:13
 */
@Component
@StepScope
public class CatProcessor extends CommonProcessor<CafeCat, Cat> {
    @Override
    public void processor(Cat o, CafeCat cafeCat) {
        //o.setId(cafeCat.getId());
        o.setCataddress(cafeCat.getCataddress());
        o.setCatage(cafeCat.getCatage());
        o.setCatname(cafeCat.getCatname());
        o.setComment01(cafeCat.getComment01());
        o.setComment02(cafeCat.getComment02());
        o.setComment03(cafeCat.getComment03());
        o.setComment04(cafeCat.getComment04());
        o.setComment05(cafeCat.getComment05());
        o.setComment06(cafeCat.getComment06());
        o.setComment07(cafeCat.getComment07());
        o.setComment08(cafeCat.getComment08());
        o.setComment09(cafeCat.getComment09());
        o.setComment10(cafeCat.getComment10());
        o.setComment11(cafeCat.getComment11());
        o.setComment12(cafeCat.getComment12());
        o.setComment13(cafeCat.getComment13());
        o.setComment14(cafeCat.getComment14());
        o.setComment15(cafeCat.getComment15());
        o.setComment16(cafeCat.getComment16());
        o.setComment17(cafeCat.getComment17());
        o.setComment18(cafeCat.getComment18());
        o.setComment19(cafeCat.getComment19());
        o.setComment20(cafeCat.getComment20());
    }
}
