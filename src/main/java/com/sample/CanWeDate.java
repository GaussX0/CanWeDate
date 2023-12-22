/*
    CanWeDate - system ekspercki do decydowania o potencjalnych partnerach romantycznych
    Copyright (C) 2023, Kamil Małecki, Oskar Kiliańczyk

    Niniejszy program jest wolnym oprogramowaniem; możesz go 
    rozprowadzać dalej i/lub modyfikować na warunkach Powszechnej
    Licencji Publicznej GNU, wydanej przez Fundację Wolnego
    Oprogramowania - według wersji 3-ciej tej Licencji.

    Niniejszy program rozpowszechniany jest z nadzieją, iż będzie on 
    użyteczny - jednak BEZ JAKIEJKOLWIEK GWARANCJI, nawet domyślnej 
    gwarancji PRZYDATNOŚCI HANDLOWEJ albo PRZYDATNOŚCI DO OKREŚLONYCH 
    ZASTOSOWAŃ. W celu uzyskania bliższych informacji - Powszechna 
    Licencja Publiczna GNU. 

    Z pewnością wraz z niniejszym programem otrzymałeś też egzemplarz 
    Powszechnej Licencji Publicznej GNU (GNU General Public License);
    jeśli nie - napisz do Free Software Foundation, Inc., 675 Mass Ave,
    Cambridge, MA 02139, USA.
 */

package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class CanWeDate {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession kSession = kc.newKieSession("ksession-rules");
        kSession.fireAllRules();
    }
}
