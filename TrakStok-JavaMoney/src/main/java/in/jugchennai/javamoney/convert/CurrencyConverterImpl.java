/*
 * Copyright 2013 JUGChennai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.jugchennai.javamoney.convert;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.money.Money;
import javax.money.MoneyCurrency;
import javax.money.convert.ConversionProvider;
import net.java.javamoney.ri.convert.provider.EZBCurrentConversionProvider;

/**
 *Implementation of the Currency converter 
 * 
 * @author Balaji T
 */
public class CurrencyConverterImpl extends AbstractCurrencyConverter{

    private ConversionProvider conversionProvider;

    public CurrencyConverterImpl(MoneyCurrency sourceCurrency, MoneyCurrency targetCurrency) {
        super(sourceCurrency, targetCurrency);
        try {
            conversionProvider = new EZBCurrentConversionProvider();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CurrencyConverterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Override
    protected Money doConvert(Money sourceMoney) {
        if(!conversionProvider.isAvailable(getSourceCurrecy(), getTargetCurrnecy())){
            throw new RuntimeException("Conversion between "+getSourceCurrecy() 
                    + " and "+getTargetCurrnecy()+" is not available");
        }
             
        return (Money) conversionProvider.getConverter().convert(sourceMoney, getTargetCurrnecy());
    }
    
}
