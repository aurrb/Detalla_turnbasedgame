package mcm.edu.ph.detalla_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView playername, playermp, playerhp, enemyname, enemyhp, combatlog;
    Button attack, magic, dodge, cont, exit;

    String username = "Simulator";
    int mp = 100;
    int hp = 150;
    int maxdps = 35;
    int mindps = 15;


    String mobname = "Data Ghost";
    int mobhp = 200;
    int mobmaxdps = 20;
    int mobmindps = 10;

    int turnno = 3;

    boolean dodgestate = false;
    boolean chargestate = false;
    int chargecounter = 0;
    boolean finish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playername = findViewById(R.id.playername);
        playermp = findViewById(R.id.playermp);
        playerhp = findViewById(R.id.playerhp);
        enemyname = findViewById(R.id.enemy);
        enemyhp = findViewById(R.id.enemyhp);

        combatlog = findViewById(R.id.combatlog);

        attack = findViewById(R.id.button);
        magic = findViewById(R.id.button2);
        dodge = findViewById(R.id.button3);
        cont = findViewById(R.id.button4);
        exit = findViewById(R.id.exit);

        playername.setText(String.valueOf(username));
        playermp.setText(String.valueOf(mp));
        playerhp.setText(String.valueOf(hp));

        enemyname.setText(String.valueOf(mobname));
        enemyhp.setText(String.valueOf(mobhp));

        attack.setOnClickListener(this);
        magic.setOnClickListener(this);
        dodge.setOnClickListener(this);
        cont.setOnClickListener(this);
        exit.setOnClickListener(this);

        exit.setEnabled(false);
        exit.setVisibility(View.INVISIBLE);


    }

    @Override
    public void onClick(View v) {
        combatlog.findViewById(R.id.combatlog);

        Random randomizer = new Random();
        int playerdps = randomizer.nextInt(maxdps - mindps) + mindps ;
        int monsdps = randomizer.nextInt(mobmaxdps - mobmindps) + mobmindps;

        //checks if player's turn


        switch(v.getId()) {

            //continue button
            case R.id.button4:
                if(chargestate == true) {
                    hp = hp - monsdps;
                    playerhp.setText(String.valueOf(hp));
                    combatlog.setText("The creature carresses you with its opaque claws. You take " + String.valueOf(monsdps) + " damage");
                    attack.setEnabled(false);
                    magic.setEnabled(false);
                    dodge.setEnabled(false);
                    cont.setEnabled(true);
                    chargecounter = 1;
                }
                else {
                    hp = hp - monsdps;
                    playerhp.setText(String.valueOf(hp));
                    combatlog.setText("The creature carresses you with its opaque claws. You take " + String.valueOf(monsdps) + " damage");
                    attack.setEnabled(true);
                    magic.setEnabled(true);
                    dodge.setEnabled(true);
                    cont.setEnabled(false);
                    chargecounter = 0;
                }
                if (chargecounter % 2 == 1) {
                    mobhp = mobhp - 55;
                    mp = mp - 40;
                    playermp.setText(String.valueOf(mp));
                    enemyhp.setText(String.valueOf(mobhp));
                    combatlog.setText("You release the raging data in your hand and aim towards the creature. There is recoil but you manage to deal " + String.valueOf(55) + " damage");
                    attack.setEnabled(true);
                    magic.setEnabled(true);
                    dodge.setEnabled(true);
                    cont.setEnabled(false);
                    chargestate = false;
                }
                else {
                    hp = hp - monsdps;
                    playerhp.setText(String.valueOf(hp));
                    combatlog.setText("The creature carresses you with its opaque claws. You take " + String.valueOf(monsdps) + " damage");
                    attack.setEnabled(true);
                    magic.setEnabled(true);
                    dodge.setEnabled(true);
                    cont.setEnabled(false);
                }

                if(dodgestate==true) {
                    combatlog.setText("The creature move its claws. You take " + String.valueOf(monsdps) + " damage.");
                    turnno--;
                    attack.setEnabled(true);
                    magic.setEnabled(true);
                    dodge.setEnabled(true);
                    cont.setEnabled(false);
                    if (turnno == 0) {
                        dodgestate = false;
                    }
                }

                if (hp <= 0) {
                combatlog.setText("The creature slashes at you, taking your final scraps of strength. The Simulation ends here. ");
                    attack.setEnabled(false);
                    magic.setEnabled(false);
                    dodge.setEnabled(false);
                    cont.setEnabled(false);
                    exit.setVisibility(View.VISIBLE);
                    exit.setEnabled(true);

            }
            if (mobhp <= 0) {
                combatlog.setText("You defeat the creature, after a long drawn out battle. The Simulation ends here. ");
                attack.setEnabled(false);
                magic.setEnabled(false);
                dodge.setEnabled(false);
                cont.setEnabled(false);
                exit.setVisibility(View.VISIBLE);
                exit.setEnabled(true);

            }

                break;


            case R.id.button:

                mobhp = mobhp - playerdps;
                enemyhp.setText(String.valueOf(mobhp));
                combatlog.setText("You slashed your virtual sword at the strange creature. You deal " + String.valueOf(playerdps) + " damage");

                attack.setEnabled(false);
                magic.setEnabled(false);
                dodge.setEnabled(false);
                cont.setEnabled(true);

                break;

            case R.id.button2:
                if (mp > 40) {
                    chargestate = true;
                    combatlog.setText("You raise your hand and gather data. They swirl around you.");
                    attack.setEnabled(false);
                    magic.setEnabled(false);
                    dodge.setEnabled(false);
                    cont.setEnabled(true);
                }
                else {
                    combatlog.setText("You raise your hand and gather data, but they do not respond to your call.");
                    attack.setEnabled(false);
                    magic.setEnabled(false);
                    dodge.setEnabled(false);
                    cont.setEnabled(true);


                }
                break;

            case R.id.button3:
                if (mp >= 10) {
                    hp = hp + 50;
                    mp = mp - 10;
                    playerhp.setText(String.valueOf(hp));
                    playermp.setText(String.valueOf(mp));
                    dodgestate = true;
                    combatlog.setText("You roll around, giving yourself an opportunity to heal. Your dodges make you tired, and you wont be able to cast a spell.");
                    attack.setEnabled(false);
                    magic.setEnabled(false);
                    dodge.setEnabled(false);
                    cont.setEnabled(true);
                }
                else {
                    combatlog.setText("You roll around, but the data around you refuse to give way, making you open for attack.");
                    attack.setEnabled(false);
                    magic.setEnabled(false);
                    dodge.setEnabled(false);
                    cont.setEnabled(true);
                }
                break;

            case R.id.exit:
                finish();
        }
    }
}
