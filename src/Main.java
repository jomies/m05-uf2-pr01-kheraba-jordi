import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] menuHamburgesa = new String[6][6];
        String[] menusDeMes = new String[10];
        ArrayList<Float> importTotal = new ArrayList<>();
        ArrayList<String[]> llistaComandaMenu = new ArrayList<>();
        ArrayList<String> llistaComandaNormal = new ArrayList<>();
        menuPrincipal(menuHamburgesa, menusDeMes, importTotal, llistaComandaMenu, llistaComandaNormal);
    }

    private static void menuPrincipal(String[][] menu, String[] menuDeMes, ArrayList<Float> importTotal, ArrayList<String[]> llistaComandaMenu, ArrayList<String> llistaComandaNormal){
        boolean sortir = false;
        do
        {
            System.out.println("1.-Mostrar carta\n2.-Iniciar comanda\n3.-Sortir");
            int num = llegirInt( "",1, 5);

            switch (num)
            {
                case 1:
                    System.out.println("___________Menú___________");
                    mostrarMenuHamburgueses(carregarMenuHamburgueses(menu));
                    System.out.println("_________Patates__________");
                    mostrarDeMes(carregarMenuPatates(menuDeMes));
                    System.out.println("________Suplements________");
                    mostrarDeMes(carregarMenuSuplements(menuDeMes));
                    System.out.println("_________Vegudes__________");
                    mostrarDeMes(carregarMenuVegudes(menuDeMes));
                    System.out.println("__________________________");
                    System.out.println("\n");
                    break;
                case 2:
                    carregarMenu2(menu, menuDeMes, importTotal, llistaComandaMenu, llistaComandaNormal);
                    break;
                case 3:
                    sortir = true;
                    break;

            }
        } while (!sortir);
    }

    private static void carregarMenu2(String[][] menu, String[] menuDeMes,ArrayList<Float>  importTotal, ArrayList<String[]> llistaComandaMenu, ArrayList<String> llistaComandaNormal){
        boolean sortir = false;
        do
        {
            System.out.println("________________________");
            System.out.println("|1.-Menu complet\n|2.-Demanar per separat\n|3.-Pagar\n|4.-Sortir");
            System.out.println("________________________");
            int num = llegirInt( "",1, 4);
            float preu = (float) importTotal.stream().mapToDouble(Float::doubleValue).sum();

            switch (num)
            {
                case 1:
                    menuMenuComplet(menu, menuDeMes, importTotal, llistaComandaMenu);
                    break;
                case 2:
                    menuPerCosesIndividuals(menu, menuDeMes, importTotal, llistaComandaNormal);
                    break;
                case 3:
                    pagar(llistaComandaMenu,llistaComandaNormal,preu,preu);
                    sortir = true;
                    break;
                case 4:
                    sortir = true;
                    break;

            }
        } while (!sortir);
    }

    private static float menuMenuComplet(String[][] menu, String[] menuDeMes,ArrayList<Float> importTotal,ArrayList<String[]> llistaComandaMenu) {
        //DecimalFormat formato1 = new DecimalFormat("#.00");
        boolean siono = false;
        boolean sortir = false;
        float preuTotal = 0;
        float ham = 0;
        float guard = 0;
        float veg = 0;
        float cond = 0;
        do {
            mostrarMenuHamburgueses(carregarMenuHamburgueses(menu));
            int num = llegirInt("|'Primer Plat'|", 1, 4);
            ham = retornaPreuHamburguesa(num);

            System.out.println("__|'Guarnició'|__");
            mostrarDeMes(carregarMenuPatates(menuDeMes));
            int num2 = llegirInt("Introdueixi la eleció", 1, 3);
            guard = retornaPreuPatates(num2);

            System.out.println("__|'Vegudes'|__");
            mostrarDeMes(carregarMenuVegudes(menuDeMes));
            int num3 = llegirInt("Introdueixi la eleció", 1, 4);
            veg = retornaPreuVegudes(num3);
            int num4;
            if (siONo(siono)){
                System.out.println("__|'Condimentació'|__");
                mostrarDeMes(carregarMenuSuplements(menuDeMes));
                num4 = llegirInt("Introdueixi la eleció", 1, 3);
                cond = retornaPreuCondimentacio(num4);
                preuTotal = preuMenu(ham,guard,veg,cond);
                importTotal.add(preuTotal);
                System.out.println("La seva comanda esta en procès.");
                afegiraLlistaComanda(num,num2,num3,num4,menu,menuDeMes,llistaComandaMenu);
                sortir = true;

            } else {
                num4 = 0;
                cond = 0;
                preuTotal = preuMenu(ham,guard,veg,cond);
                importTotal.add(preuTotal);
                System.out.println("La seva comanda esta en procès.");
                afegiraLlistaComanda(num,num2,num3,num4,menu,menuDeMes,llistaComandaMenu);
                sortir = true;
            }


        }while (!sortir);
        //float preu = (float) importTotal.stream().mapToDouble(Float::doubleValue).sum();
        //System.out.println(formato1.format(preu) + "€");
        return preuTotal;
    }

    private static float menuPerCosesIndividuals(String[][] menu, String[] menuDeMes,ArrayList<Float> importTotal, ArrayList<String> llistaComandaNormal) {
        boolean sortir = false;
        float preuTotal = 0;
        do {
            System.out.println("________________________");
            System.out.println("|1.-Carta\n|2.-Hamburgueses\n|3.-Guarniciona\n|4.-Vegudes\n|5.-Condiments\n|6.-Sortir");
            System.out.println("________________________");
            int num = llegirInt( "",1, 6);

            switch (num)
            {
                case 1:
                    System.out.println("___________Menú___________");
                    mostrarMenuHamburgueses(carregarMenuHamburgueses(menu));
                    System.out.println("_________Patates__________");
                    mostrarDeMes(carregarMenuPatates(menuDeMes));
                    System.out.println("________Suplements________");
                    mostrarDeMes(carregarMenuSuplements(menuDeMes));
                    System.out.println("_________Vegudes__________");
                    mostrarDeMes(carregarMenuVegudes(menuDeMes));
                    System.out.println("__________________________");
                    System.out.println("\n");
                    break;
                case 2:
                    float ham = 0;
                    mostrarMenuHamburgueses(carregarMenuHamburgueses(menu));
                    int num2 = llegirInt("", 1, 4);
                    ham = retornaPreuHamburguesa(num2);
                    importTotal.add(ham);
                    afegirHamIndividual(menu, num2, llistaComandaNormal);
                    break;
                case 3:
                    float guard = 0;
                    mostrarDeMes(carregarMenuPatates(menuDeMes));
                    int num3 = llegirInt("", 1, 3);
                    guard = retornaPreuPatates(num3);
                    importTotal.add(guard);
                    afegirAltresIndividual(carregarMenuPatates(menuDeMes), num3,llistaComandaNormal);
                    break;
                case 4:
                    float veg = 0;
                    mostrarDeMes(carregarMenuVegudes(menuDeMes));
                    int num4 = llegirInt("", 1, 4);
                    veg = retornaPreuVegudes(num4);
                    importTotal.add(veg);
                    afegirAltresIndividual(carregarMenuVegudes(menuDeMes), num4,llistaComandaNormal);
                    break;
                case 5:
                    float cond = 0;
                    mostrarDeMes(carregarMenuSuplements(menuDeMes));
                    int num5 = llegirInt("", 1, 3);
                    cond = retornaPreuCondimentacio(num5);
                    importTotal.add(cond);
                    afegirAltresIndividual(carregarMenuSuplements(menuDeMes), num5,llistaComandaNormal);
                    break;
                case 6:
                    sortir = true;
                    break;

            }
        }while (!sortir);
        return preuTotal;
    }

    private static void afegiraLlistaComanda(int num, int num2, int num3, int num4, String[][] menu, String[] menuDeMes, ArrayList<String[]>  llistaComandaMenu){
        String[] menux =new String[4];
        String ham = "";
        menu = carregarMenuHamburgueses(menu);
        if (num == 1){
            ham = menu[0][0];
            menux[0] = ham;
        } else if (num == 2) {
            ham = menu[1][0];
            menux[0] = ham;
        } else if (num == 3){
            ham = menu[2][0];
            menux[0] = ham;
        } else if (num == 4) {
            ham = menu[3][0];
            menux[0] = ham;
        }
        String guard = "";
        menuDeMes = carregarMenuPatates(menuDeMes);
        if (num2 == 1){
            guard = menuDeMes[0];
            menux[1] = guard;
        } else if (num2 == 2) {
            guard = menuDeMes[1];
            menux[1] = guard;
        } else if (num2 == 3){
            guard = menuDeMes[2];
            menux[1] = guard;
        }
        String veg = "";
        menuDeMes = carregarMenuVegudes(menuDeMes);
        if (num3 == 1){
            veg = menuDeMes[0];
            menux[2] = veg;
        } else if (num3 == 2) {
            veg = menuDeMes[1];
            menux[2] = veg;
        } else if (num3 == 3){
            veg = menuDeMes[2];
            menux[2] = veg;
        }else if (num3 == 4){
            veg = menuDeMes[3];
            menux[2] = veg;
        }
        String comp = "";
        menuDeMes = carregarMenuSuplements(menuDeMes);
        if (num4 == 1){
            comp = menuDeMes[0];
            menux[3] = comp;
        } else if (num4 == 2) {
            comp = menuDeMes[1];
            menux[3] = comp;
        } else if (num4 == 3){
            comp = menuDeMes[2];
            menux[3] = comp;
        }else if (num4 == 0 ){
            veg = "No Complement";
            menux[3] = veg;
        }
        llistaComandaMenu.add(menux);

    }

    private static void afegirHamIndividual(String[][] menu, int num, ArrayList<String> llistaComandaNormal){
        String ham = "";
        menu = carregarMenuHamburgueses(menu);
        if (num == 1){
            ham = menu[0][0];
            llistaComandaNormal.add(ham);
        } else if (num == 2) {
            ham = menu[1][0];
            llistaComandaNormal.add(ham);
        } else if (num == 3){
            ham = menu[2][0];
            llistaComandaNormal.add(ham);
        } else if (num == 4) {
            ham = menu[3][0];
            llistaComandaNormal.add(ham);
        }
    }

    private static void afegirAltresIndividual(String[] menuDeMes, int num, ArrayList<String> llistaComandaNormal){
        String ham = "";
        if (num == 1){
            ham = menuDeMes[0];
            llistaComandaNormal.add(ham);
        } else if (num == 2) {
            ham = menuDeMes[1];
            llistaComandaNormal.add(ham);
        } else if (num == 3){
            ham = menuDeMes[2];
            llistaComandaNormal.add(ham);
        } else if (num == 4) {
            ham = menuDeMes[3];
            llistaComandaNormal.add(ham);
        }
    }

    private static float retornaPreuCondimentacio(int num) {
        float con = 0;
        if (num == 1) {
            con = 4f;
        } else if (num == 2) {
            con = 5f;
        } else if (num == 3) {
            con = 3.5f;
        }
        return con;
    }

    private static float preuMenu(float ham, float guard, float veg, float con){
        float sumaTotalFinal = ham + guard + veg + con;
        float descomptePerMenu = sumaTotalFinal * 0.10f;
        float preuFinal = sumaTotalFinal - descomptePerMenu;
        return preuFinal;
    }

    private static float retornaPreuPatates(int num){
        float con = 0;
        if (num == 1) {
            con = 2.5f;
        } else if (num == 2) {
            con = 3f;
        } else if (num == 3) {
            con = 4.5f;
        }
        return con;
    }


    private static float retornaPreuVegudes(int num) {
        float con = 0;
        if (num == 1) {
            con = 1.5f;
        } else if (num == 2) {
            con = 2f;
        } else if (num == 3) {
            con = 1.75f;
        } else if (num == 4) {
            con = 1.75f;
        }
        return con;
    }


    private static float retornaPreuHamburguesa(int num) {
        float con = 0;
        if (num == 1) {
            con = 6.5f;
        } else if (num == 2) {
            con = 7f;
        } else if (num == 3) {
            con = 7.5f;
        } else if (num == 4) {
            con = 8.5f;
        }
        return con;
    }


    private static String[][] carregarMenuHamburgueses(String[][] menu){
        menu = new String[][]{{"Hmburgues Runes", "carn", "bacon", "ou"},
                {"Hmburgues Pollo", "pollo", "bacon", "ou"},
                {"Hmburgues Barbacoa", "pollo", "bacon", "ou"},
                {"Hmburgues De lux", "pollo", "bacon", "ou"}
        };
        return menu;
    }
    private static String[] carregarMenuSuplements(String[] menu){
        menu = new String[]{"Nuggets 9u", "Finguers de pollo 6u", "Amanida amb tomaquet"};
        return menu;
    }

    private static String[] carregarMenuPatates(String[] menu){
        menu = new String[]{"Patates normals", "Patates de luxe", "Patates bacon"};
        return menu;
    }

    private static String[][] mostrarMenuHamburgueses(String[][] menu){
        for (int i = 0; i < menu.length; i++) {
            System.out.print(menu[i][0] + ": ");
            for (int j = 1; j < menu[i].length; j++) {
                System.out.print(menu[i][j] + " ");
            }
            System.out.println("\n");
        }
        return menu;
    }

    private static String[] mostrarDeMes(String[] menuDeMes){
        for (int i = 0; i < menuDeMes.length; i++) {
            System.out.println(menuDeMes[i]);
        }
        return menuDeMes;
    }

    private static String[] carregarMenuVegudes(String[] menu){
        menu = new String[]{"Aigua", "Cocacola", "Fanta Tronja", "Fanta Llimona"};
        return menu;
    }
    private static int llegirInt(String missatge, int min, int max) {
        Scanner llegir = new Scanner(System.in);
        int x = 0;
        boolean valorCorrecte = false;
        do{
            System.out.println(missatge);
            valorCorrecte = llegir.hasNextInt();
            if (!valorCorrecte){
                System.out.println("ERROR: Valor no enter.");
                llegir.nextLine();
            }else{
                x = llegir.nextInt();
                llegir.nextLine();
                if (x < min || x > max){
                    System.out.println("Opció no vàlida");
                    valorCorrecte = false;
                }
            }
        }while(!valorCorrecte);
        return x;
    }
    public static void pagar(ArrayList<String[]> llistaComandaMenu, ArrayList<String> llistaComandaNormal, float preuFinal , float mostrarPreuFinal)
    {
        Scanner input = new Scanner(System.in);

        final float[] DINERS = {0.01f, 0.02f, 0.05f, 0.10f, 0.20f, 0.50f, 1.0f, 2.0f, 5.0f, 10.0f, 20.0f, 50.0f,100.0f};
        final String ERROR = " ERROR: Això no és una moneda/bitllet";
        final String ERROR_MONEDES = " ERROR: Aquesta moneda/bitllet no existeix";

        boolean pagamentCorrecte = false, controlErrors = false, comprobarMoneda = false, tiquet = false;
        float importClient = 0, canvi = 0, importTotal = 0.00f;

        do
        {
            do
            {
                System.out.printf("Import total a pagar: %.2f€\n", preuFinal);
                System.out.print("Introdueixi els diners: ");
                controlErrors = input.hasNextFloat();

                if (!controlErrors)
                {
                    System.out.println(ERROR);
                }
                else
                {
                    importClient = input.nextFloat();
                    comprobarMoneda = comprobarMoneda(DINERS, importClient, comprobarMoneda);
                    if (comprobarMoneda)
                    {
                        if (preuFinal > 0.00f)
                        {
                            if (importClient > preuFinal || (importClient - preuFinal) == 0)
                            {
                                importTotal += importClient;
                                canvi = importClient - preuFinal;

                                System.out.printf("Pagament correcte, no oblidi el canvi de %.2f€ \n", canvi);
                                pagamentCorrecte = true;

                                tiquet = siONo(tiquet);
                                if (tiquet)
                                {
                                    tiquet(llistaComandaMenu,llistaComandaNormal, mostrarPreuFinal, canvi, importTotal);
                                }
                                break;
                            }
                            else
                            {
                                preuFinal -= importClient;
                                importTotal += importClient;
                            }
                        }
                    }
                    else
                    {
                        System.out.println(ERROR_MONEDES);
                        controlErrors = false;
                    }
                }
                input.nextLine();
            } while (!controlErrors);
        } while (!pagamentCorrecte);
    }

    private static void mostrarMenuHamburgueses(ArrayList<String[]> llistaComandaMenu){
        int i = 0;
        for (String[] element: llistaComandaMenu){
            i++;
            System.out.println("|   Menu" + i + ":                                                  |");
            System.out.println("| " + Arrays.toString(element));
            System.out.println("|                                                           |");
        }

    }

    private static void mostrarAltres(ArrayList<String> llistaComandaNormal){
        System.out.println("|   Individual:                                             |");
        for (String element: llistaComandaNormal){
            System.out.println("| " + element);
        }

    }

    public static boolean siONo(boolean tiquet)
    {
        Scanner input = new Scanner(System.in);
        final String ERROR = " ERROR: Opció no vàlida";
        String siNo;

        do
        {
            System.out.print("Vols tiquet? (Si/No) ");
            siNo = input.nextLine();

            if (Objects.equals(siNo, "Si"))
            {
                tiquet = true;
            }
            else if (Objects.equals(siNo, "No"))
            {
                tiquet = false;
            }
            else
            {
                System.out.println(ERROR);
            }
        } while (!Objects.equals(siNo, "Si") && !Objects.equals(siNo, "No"));

        return tiquet;
    }

    public static void tiquet(ArrayList<String[]> llistaComandaMenu, ArrayList<String> llistaComandaNormal, float preuFinal, float canvi, float importTotal)
    {
        final String GUIO = "|-----------------------------------------------------------|";
        final String TITOL = "|\t\t\t\t\t\t\tREBUT \t\t\t\t\t\t\t|";
        final String ESPAI_BUIT = "|                                                           |";

        float mostrarFloat = 0.00f;

        System.out.println();
        System.out.println(GUIO);
        System.out.println(TITOL);
        System.out.println(GUIO);
        System.out.println("|\tComanda:\t\t\t\t\t\t\t\t\t\t\t\t|");
        mostrarMenuHamburgueses(llistaComandaMenu);
        System.out.println(ESPAI_BUIT);
        mostrarAltres(llistaComandaNormal);

        System.out.println(GUIO);

        System.out.printf("|\tTOTAL........................................ %.2f€\t|\n", preuFinal);
        System.out.printf("|\tIMPORT....................................... %.2f€\t|\n", importTotal);
        System.out.printf("|\tCANVI........................................ %.2f€\t\t|\n", canvi);

        System.out.println(GUIO);
        System.out.printf("\n");
        System.out.printf("\n");
    }

    public static boolean comprobarMoneda(final float[] diners, float importClient, boolean comprobarMoneda)
    {
        comprobarMoneda = false;
        for (int i = 0; i < diners.length; i++)
        {
            if (importClient == diners[i])
            {
                comprobarMoneda = true;
                break;
            }
        }
        return comprobarMoneda;
    }

}