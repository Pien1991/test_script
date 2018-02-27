package util;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

/**
 * Created by ShepardPin on 12/2/2018.
 */
public interface EnhancedExpectedCondition<T> extends Function<SearchContext, T > {


    Function< ? extends SearchContext , T > injectConditionFactors(By by, SearchContext ... var1);
}
