package com.doodeec.alertdialog;

import android.content.DialogInterface;
import android.support.annotation.IntDef;

/**
 * @author Dusan Bartos
 *         Created on 21.06.2017.
 */
@IntDef({DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL, DialogInterface.BUTTON_POSITIVE})
public @interface DialogButtonType {}
