/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute;

import org.retropipes.diane.Diane;
import org.retropipes.diane.gui.dialog.CommonDialogs;
import org.retropipes.diane.integration.Integration;

import com.puttysoftware.loopchute.prefs.PreferencesLauncher;

public class LoopChute {
    // Constants
    private static Application application;
    private static final String PROGRAM_NAME = "loopchute";
    private static final String ERROR_MESSAGE = "Perhaps a bug is to blame for this error message.\n"
	    + "Include the error log with your bug report.\n" + "Email bug reports to: products@puttysoftware.com\n"
	    + "Subject: loopchute Bug Report";
    private static final String ERROR_TITLE = "loopchute Error";

    // Methods
    public static Application getApplication() {
	return LoopChute.application;
    }

    public static void logError(final Throwable t) {
	CommonDialogs.showErrorDialog(LoopChute.ERROR_MESSAGE, LoopChute.ERROR_TITLE);
	Diane.handleError(t);
    }

    public static void main(final String[] args) {
	try {
	    Diane.installDefaultErrorHandler(LoopChute.PROGRAM_NAME);
	    // Integrate with host platform
	    Integration i = Integration.integrate();
	    LoopChute.application = new Application();
	    LoopChute.application.postConstruct();
	    LoopChute.application.playLogoSound();
	    LoopChute.application.getGUIManager().showGUI();
	    // Register platform hooks
	    i.setAboutHandler(LoopChute.application.getAboutDialog());
	    i.setOpenFileHandler(LoopChute.application.getMazeManager());
	    i.setPreferencesHandler(new PreferencesLauncher());
	    i.setQuitHandler(LoopChute.application.getGUIManager());
	    // Set up Common Dialogs
	    CommonDialogs.setDefaultTitle(LoopChute.PROGRAM_NAME);
	    CommonDialogs.setIcon(LoopChute.application.getMicroLogo());
	} catch (final Throwable t) {
	    LoopChute.logError(t);
	}
    }
}
