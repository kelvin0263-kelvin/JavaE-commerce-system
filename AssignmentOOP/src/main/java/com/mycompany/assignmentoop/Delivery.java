
package com.mycompany.assignmentoop;


/**
 *
 * @author 60164
 */
public interface Delivery {

    String deliverySelection(double itemTotalWeight);

    double standardDeliveryFeeCalc(double itemTotalWeight);

    double bulkyDeliveryFeeCalc(double itemTotalWeight);

}
