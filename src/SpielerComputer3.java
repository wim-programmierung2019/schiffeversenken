
public class SpielerComputer3 extends Spieler {

    // Merken in welchem Zug der Spieler gerade ist
    protected int koordinateX = 0;
    protected int koordinateY = 0;
    protected boolean getroffen = false;
    protected int speicherX = 0;
    protected int speicherY = 0;
    protected int counter = 0;
    protected boolean back = false;
    protected Koordinate[] myShots = new Koordinate[1000];
    protected int zugNummer = 0;

    public SpielerComputer3(String name) {
        super(name);
    }

    @Override
    public Koordinate schuss() {
        // Idee: Schrittweise jede Koordinate abarbeiten
        // Prüfung ob Koordinate schon beschossen
        for (int i = 0; i < zugNummer; i++) {
            if (myShots[i].getX() == koordinateX && myShots[i].getY() == koordinateY) {
                if (koordinateX == 9 || koordinateY == 9) {
                    if (koordinateX == 9 && koordinateY == 9) {
                        koordinateX = 0;
                        koordinateY = 0;
                    } else {
                        if (koordinateX == 9) {
                            koordinateX = 0;
                        }
                        if (koordinateY == 9) {
                            koordinateY = 0;
                        }
                    }
                }
                koordinateX = Helfer.zufallszahl(0,9);
                koordinateY = Helfer.zufallszahl(0,9);
            }
        }
        int x = koordinateX;
        int y = koordinateY;
        myShots[zugNummer] = new Koordinate(koordinateX, koordinateY);
        zugNummer++;
        return new Koordinate(x, y);

    }

    @Override
    public void ergebnis(TrefferTyp ergebnisTyp, Koordinate position) {

        TrefferTyp treffer = TrefferTyp.TREFFER;
        TrefferTyp wasser = TrefferTyp.WASSER;
        TrefferTyp versenkt = TrefferTyp.VERSENKT;
        if (ergebnisTyp == treffer) {
            if (getroffen == false) {
                speicherX = koordinateX;
                speicherY = koordinateY;
            }

            treffer(position.getX(), position.getY());
        }
        if (ergebnisTyp == wasser) {
            if (getroffen == false) {
                if (position.getY() == 9 || position.getX() == 9) {
                    if (position.getY() == 9 && position.getX() == 9) {
                        koordinateY = 0;
                        koordinateX = 2;
                    } else {

                        if (position.getY() == 9) {
                            koordinateY = 0;
                        }
                        if (position.getX() == 9) {
                            koordinateX = 0;
                        }
                    }

                } else {
                    koordinateY++;
                    koordinateX++;
                }
            } else {
                counter++;
                back = false;
                treffer(position.getX(), position.getY());
            }

        }

        if (ergebnisTyp == versenkt) {
            getroffen = false;
            counter = 0;
            returnTaktik();
            // rückkehr
        }

        // Taktik umstellen
        // Mir ist egal, was die Antwort ist
        // hier muss geklärt werden ob getroffen
        // dann anpassung der Taktik
        // gleichzeitig abspeichern des wertes
        // ggf. einen boolean setzen o.ä.
    }

    public void treffer(int x, int y) {
        getroffen = true;
        if (counter == 0) {
            if (koordinateY == 9) {
                koordinateY = 0;
            } else {
                koordinateY++;
            }
        }
        if (counter == 1) {
            if (back == false) {
                back = true;
                koordinateY = speicherY;
                koordinateX = speicherX;
            }
            if (koordinateY == 0) {
                koordinateY = 9;
            } else {
                koordinateY--;
            }

        }
        if (counter == 2) {
            if (back == false) {
                back = true;
                koordinateY = speicherY;
                koordinateX = speicherX;
            }
            if (koordinateX == 9) {
                koordinateX = 0;
            } else {
                koordinateX++;
            }
        }
        if (counter == 3) {
            if (back == false) {
                back = true;
                koordinateY = speicherY;
                koordinateX = speicherX;
            }
            if (koordinateX == 0) {
                koordinateX = 9;
            } else {
                koordinateX--;
            }
        }
        /*
         * if (koordinateY == 9 || koordinateX == 9) { if (koordinateY == 9) {
         * koordinateY = 0; } } koordinateY++;
         */
    }

    public void returnTaktik() {
        if(getroffen == true){
        myShots[zugNummer] = new Koordinate(koordinateX + 1, koordinateY);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX - 1, koordinateY);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX + 1, koordinateY + 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX + 1, koordinateY - 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX - 1, koordinateY + 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX - 1, koordinateY - 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX, koordinateY + 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX, koordinateY - 1);
        zugNummer++;
        koordinateX = speicherX;
        koordinateY = speicherY;
        myShots[zugNummer] = new Koordinate(koordinateX + 1, koordinateY);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX - 1, koordinateY);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX + 1, koordinateY + 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX + 1, koordinateY - 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX - 1, koordinateY + 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX - 1, koordinateY - 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX, koordinateY + 1);
        zugNummer++;
        myShots[zugNummer] = new Koordinate(koordinateX, koordinateY - 1);
        zugNummer++;
        if (koordinateY == 9 || koordinateX == 9) {
            if (koordinateY == 9 && koordinateX == 9) {
                koordinateY = 0;
                koordinateX = 2;
            } else {
                if (koordinateY == 9) {
                    koordinateY = 0;
                }
                if (koordinateX == 9) {
                    koordinateX = 0;
                }
            }
        } else {
            koordinateX++;
            koordinateY++;
        }
    }else{
        if (koordinateY == 9 || koordinateX == 9) {
            if (koordinateY == 9 && koordinateX == 9) {
                koordinateY = 0;
                koordinateX = 2;
            } else {
                if (koordinateY == 9) {
                    koordinateY = 0;
                }
                if (koordinateX == 9) {
                    koordinateX = 0;
                }
            }
        } else {
            koordinateX++;
            koordinateY++;
        }
    }
    

    }

    @Override
    public Feld erzeugeFeld() {
        // 1 Schlachtschiff = 5
        Schiff schlachtschiff = new Schiff(5);
        // 2 Kreuzer = 4
        Schiff kreuzer1 = new Schiff(4);
        Schiff kreuzer2 = new Schiff(4);
        // 3 Zerstoerer = 3
        Schiff zerstoerer1 = new Schiff(3);
        Schiff zerstoerer2 = new Schiff(3);
        Schiff zerstoerer3 = new Schiff(3);
        // 4 Uboote = 2
        Schiff uboot1 = new Schiff(2);
        Schiff uboot2 = new Schiff(2);
        Schiff uboot3 = new Schiff(2);
        Schiff uboot4 = new Schiff(2);

        // Neues Feld anlegen
        Feld neuesFeld = new Feld(getSpiel().getFeldGroesse());

        // Schiffe platzieren
        neuesFeld.setzeSchiff(schlachtschiff, new Koordinate(0, 0), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(kreuzer1, new Koordinate(2, 0), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(kreuzer2, new Koordinate(2, 5), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(zerstoerer1, new Koordinate(4, 0), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(zerstoerer2, new Koordinate(4, 4), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(zerstoerer3, new Koordinate(6, 0), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(uboot1, new Koordinate(8, 3), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(uboot2, new Koordinate(6, 4), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(uboot3, new Koordinate(6, 7), Orientierung.VERTIKAL);
        neuesFeld.setzeSchiff(uboot4, new Koordinate(8, 0), Orientierung.VERTIKAL);

        return neuesFeld;
    }
}