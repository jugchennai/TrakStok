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

import javax.money.Money;
import javax.money.MoneyCurrency;

/**
 * Abstract implementation of CurrencyConverter.It performs the basic validation
 * and sets the source and target currencies. It provides a template method
 * to the subclasses to implement their own conversion implementations.
 * 
 * @author Balaji T
 */
public abstract class AbstractCurrencyConverter implements CurrencyConverter{
    
    private MoneyCurrency sourceCurrency;
    private MoneyCurrency targetCurrency;

    public AbstractCurrencyConverter(MoneyCurrency sourceCurrency, MoneyCurrency targetCurrency) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    @Override
    public final MoneyCurrency getSourceCurrecy() {
        return sourceCurrency;
    }

    @Override
    public final MoneyCurrency getTargetCurrnecy() {
        return targetCurrency;
    }

    @Override
    public final Money convert(Money sourceMoney) {
        if(sourceMoney == null){
            throw new IllegalArgumentException("Source money should not be null");
        }
        if(!sourceMoney.getCurrency().equals(sourceCurrency)){
            throw new IllegalArgumentException("Source money currency should be of type "+sourceCurrency.getCurrencyCode());
        }
        return doConvert(sourceMoney);        
    }

    protected abstract Money doConvert(Money sourceMoney);
    
}
