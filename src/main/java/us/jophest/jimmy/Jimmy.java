package us.jophest.jimmy;


import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pircbotx.Colors;
import org.jsoup.nodes.Document;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.InviteEvent;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Jimmy extends ListenerAdapter {

    public static PircBotX bot = new PircBotX();

    public static void main(String[] args) throws Exception {


        bot.setName("Cobby");
        bot.setLogin("Cobby");
        bot.getListenerManager().addListener(new Jimmy());

        bot.connect("jophest.us", 1337, "Cobby");


        bot.joinChannel("#chemnstuff");
        bot.joinChannel("#huskehhh");
        bot.joinChannel("#cobby");
        bot.joinChannel("#DSH105");
        bot.joinChannel("#entityapi");
        bot.joinChannel("#gomeow");
        bot.joinChannel("#hoolean");
        bot.joinChannel("#jacklin213");
        bot.joinChannel("#jophestus");
        bot.joinChannel("#LividiusGaming");
        bot.joinChannel("#frostalf");
        bot.joinChannel("#dra");
        bot.joinChannel("#hawkfalcon");
        bot.sendMessage("#jophestus", "I'm alive!");


    }

    public static Object getConfig(Object obj) {
        Map map = null;
        try {
            YamlReader reader = new YamlReader(new FileReader("config.yml"));
            Object conf = reader.read();
            map = (Map) conf;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map.get(obj).toString();
    }

    public String notifname;
    static String channelName = "";
    static File f = new File("");
    ConcurrentHashMap<String, List<String>> notifs = new ConcurrentHashMap<>();
    List<String> notiflist = new ArrayList<String>();

    public void onLoad(ConnectEvent event) {

    }

    public static void itpa(String name, String state) throws Exception {
        URL yahoo = new URL(getConfig("itpalink") + "?name=" + name + "&state=" + state);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream())
        );
        String inputLine;

        while ((inputLine = in.readLine()) != null)

            in.close();
    }

    //public void onDeath()
    public void onInvite(InviteEvent event) {
        channelName = event.getChannel();
        f = new File(channelName);
        if (f.exists()) {
            bot.sendMessage(event.getUser(), "You just invited Cobby to " + channelName + ". Unfortunately for some reason your channel has been blacklisted. Please contact JOPHESTUS if you believe this to be an error.");
        } else {
            bot.joinChannel(event.getChannel());

        }
    }

    public void onMessage(final MessageEvent event) {

        String usr = event.getUser().getNick();
        final String[] line = event.getMessage().split(" ");
        if (line[0].equalsIgnoreCase("!pizza")) {

            if (line.length == 1) {


                event.getBot().sendAction(event.getChannel(),
                        "makes everyone a pizza!");


            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "makes " + line[1] + " a pizza!");
            }

        } else if (event.getMessage().equalsIgnoreCase("!recipe")) {

            event.getBot().sendMessage(event.getUser(),
                    "Basic Pizza Base Recipe. Serves: 1");
            event.getBot()
                    .sendMessage(
                            event.getUser(),
                            "[Preheat oven 100C] 1 cup flour, 1/2 cup warm water, 1/2 tsp sugar, 1/2 tsp salt, 1tsp dried yeast");
            event.getBot()
                    .sendMessage(event.getUser(),
                            "Mix all ingredients together, knead dough (add flour where necessary).");
            event.getBot()
                    .sendMessage(event.getUser(),
                            "Prove (allow to rise) in a warm oven (100 C) with the door open for 5-10 mins");
            event.getBot()
                    .sendMessage(event.getUser(),
                            "Knead, roll out dough, and apply desired toppings. Cook at 200C");

        } else if (event.getMessage().equalsIgnoreCase("!a")) {

            event.getBot().sendAction(event.getChannel(),
                    "agrees");

        } else if (event.getMessage().equalsIgnoreCase("!cobby help")) {

            event.getBot().sendMessage(event.getUser(), "!a - Agree");
            event.getBot().sendMessage(event.getUser(), "!pizza <name> - Make someone a pizza");
            event.getBot().sendMessage(event.getUser(), "!recipe - Tells you a basic pizza base recipe");
            event.getBot().sendMessage(event.getUser(), "!tr <name> - Give someone a treat");
            event.getBot().sendMessage(event.getUser(), "!sm <name> - Smite someone");
            event.getBot().sendMessage(event.getUser(), "!cd <name> - Condemn someone");
            event.getBot().sendMessage(event.getUser(), "!h <name> - Hug someone");
            event.getBot().sendMessage(event.getUser(), "!wa <name> - Wave at someone");
            event.getBot().sendMessage(event.getUser(), "!spam <user> - Spams someone");
            event.getBot().sendMessage(event.getUser(), "!we <user> - Welcomes someone");
            event.getBot().sendMessage(event.getUser(), "!l <user> - Laughs at someone");
            event.getBot().sendMessage(event.getUser(), "!troll <user> - Trolls someone");
            event.getBot().sendMessage(event.getUser(), "!bl <user> - Blames someone");
            event.getBot().sendMessage(event.getUser(), "!bu <user> - Checks your bukkit profile");
        } else if (line[0].equalsIgnoreCase("!pr")) {

            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "gives everyone a Christmas present!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "gives " + line[1] + " a Christmas present!");
            }


        } else if (event.getMessage().equalsIgnoreCase("!cobby leave")) {
            if (event.getUser().getNick().equalsIgnoreCase("JOPHESTUS")) {
                bot.partChannel(event.getChannel(), "JOPH told me to");
            } else {
                event.respond("You can't do that");
            }
        } else if (event.getMessage().startsWith("!cobby join")) {
            if (event.getUser().getNick().equalsIgnoreCase("JOPHESTUS")) {
                bot.joinChannel(line[2]);
                event.getBot().sendMessage(line[2], "I was told to join by JOPHESTUS. If you don't want me here, please kick me.");

            } else {
                event.respond("You can't do that");
            }
        } else if (event.getMessage().startsWith("!cobby blacklist")) {
            if (event.getUser().getNick().equalsIgnoreCase("JOPHESTUS")) {
                channelName = line[2];
                f = new File(line[2]);

                try {
                    f.createNewFile();
                    event.respond("Channel " + line[2] + " has been blacklisted.");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                event.respond("You can't do that");
            }
        } else if (event.getMessage().startsWith("!cobby topic")) {
            if (event.getChannel().isOp(event.getUser())) {

                StringBuilder b = new StringBuilder();
                for (int i = 2; i < line.length; i++) {
                    if (i != 2)
                        b.append(" ");
                    b.append(line[i]);
                }
                event.getBot().setTopic(event.getChannel(), b.toString());
            } else {
                event.respond("You can't do that");
            }
        } else if (line[0].equalsIgnoreCase("!sm")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "smites " + "everyone!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "smites " + line[1] + "!");


            }
        } else if (line[0].equalsIgnoreCase("!cd")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "condemns " + "everyone!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "condemns " + line[1] + "!");

            }
        } else if (line[0].equalsIgnoreCase("!bu")) {
            String name;
            if (line.length == 1) {
                name = event.getUser().getNick();
            } else {
                name = line[1];
            }

            int posts;
            int likes;
            long timestamp; //Example -> in ms

            try {
                URL statsURL;
                URLConnection con = new URL("http://forums.bukkit.org/members/?username=" + name).openConnection();

                con.connect();

                InputStream is = con.getInputStream();
                statsURL = con.getURL();
                URLConnection vgd = new URL("http://is.gd/create.php?format=simple&url=" + statsURL.toString()).openConnection();
                InputStream vgdIs = con.getInputStream();
                String shorturl = vgd.getURL().toString();
                vgdIs.close();
                is.close();
                Document shortnedget = Jsoup.connect(shorturl).get();
                String link = shortnedget.text();
                String followerAmount;
                Document followers = Jsoup.connect(statsURL.toString()).get();
                Elements fol = followers.select("a.count");
                if (!fol.isEmpty()) {
                    String folFrag = fol.last().toString();
                    Document folz = Jsoup.parseBodyFragment(folFrag);
                    followerAmount = folz.text();
                } else {
                    followerAmount = "0";
                }


                Document doc = Jsoup.connect(statsURL + "mini-stats.xml").get();

                if (doc.location().equalsIgnoreCase("http://forums.bukkit.org/members/?username=" + name + "mini-stats.xml")) {
                    bot.sendMessage(event.getChannel(), "User not found :(");
                } else if (doc.text().equals("This member limits who may view their full profile.")) {
                    bot.sendMessage(event.getChannel(), "This user limits who may view their full profile");
                } else {

                    Element messages = doc.select("message_count").first();


                    String messagesS = messages.text();

                    posts = Integer.parseInt(messagesS);
                    Element likecount = doc.select("like_count").first();
                    String likesS = likecount.text();

                    likes = Integer.parseInt(likesS);
                    Element register = doc.select("register_date").first();
                    String date = register.text();
                    Element usern = doc.select("username").first();
                    String user = usern.text();

                    timestamp = Integer.parseInt(date);
                    timestamp = timestamp * 1000;
                    Date d = new Date(timestamp);
                    double likesr = likes;
                    double postsr = posts;
                    double ratio = likesr / postsr;
                    double finalratio = (double) Math.round(ratio * 1000) / 1000;


                    bot.sendMessage(event.getChannel(), Colors.OLIVE + Colors.BOLD + "Bukkit User: " + Colors.BLACK + user + " | " + link);
                    bot.sendMessage(event.getChannel(), "Messages: " + Colors.BOLD + posts + Colors.NORMAL + " | Likes: " + Colors.BOLD + likes + Colors.NORMAL + " | LtP: " + Colors.BOLD + finalratio + Colors.NORMAL + " | Followers: " + Colors.BOLD + followerAmount);
                    bot.sendMessage(event.getChannel(), "Registered on " + Colors.UNDERLINE + d);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (line[0].equalsIgnoreCase("!tr")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "gives everyone a treat!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "gives " + line[1] + " a treat!");

            }
        } else if (line[0].equalsIgnoreCase("!h")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "hugs " + "everyone!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "hugs " + line[1] + "!");

            }
        } else if (line[0].equalsIgnoreCase("!wa")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "waves at " + "everyone!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "waves at " + line[1] + "!");

            }
        } else if (line[0].equalsIgnoreCase("!setitpa")) {
            if (event.getUser().getNick().equalsIgnoreCase("JOPHESTUS")) {
                try {
                    itpa(line[1], line[2]);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {

                event.respond("You can't do that");
            }

        } else if (line[0].equalsIgnoreCase("!l")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "laughs at " + "everyone!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "laughs at " + line[1] + "!");

            }
        } else if (line[0].equalsIgnoreCase("!spam")) {
            if (line[1] != null) {
                event.getBot().sendMessage(event.getChannel(),
                        line[1] + " " + line[1] + " " + line[1] + " " + line[1] + " " + line[1] + " " + "!");

            }
        } else if (line[0].equalsIgnoreCase("!we")) {
            if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(), "welcomes " + line[1]);

            }
        } else if (line[0].equalsIgnoreCase("!troll")) {
            if (line[1] != null) {
                event.getBot().sendMessage(event.getChannel(), line[1] + " got trolled!");

            }
        } else if (line[0].equalsIgnoreCase("!talk")) {
            if (event.getUser().getNick().equals("JOPHESTUS")) {
                StringBuilder b = new StringBuilder();
                for (int i = 2; i < line.length; i++) {
                    if (i != 2)
                        b.append(" ");
                    b.append(line[i]);

                }
                bot.sendMessage(line[1], b.toString());
            } else {
                event.respond("You can't do that, lel.");
            }
        } else if (line[0].equalsIgnoreCase("!bl")) {
            if (line.length == 1) {
                event.getBot().sendAction(event.getChannel(),
                        "blames Hoolean!");
            } else if (line[1] != null) {
                event.getBot().sendAction(event.getChannel(),
                        "blames " + line[1] + "!");

            }
        } else if (line[0].equalsIgnoreCase("!notif")) {

            String sender = event.getUser().getNick();
            String target = line[1];
            System.out.println(target + " " + sender);

            if (notifs.get(target.toLowerCase()) != null) {
                try {
                    notiflist = notifs.get(target.toLowerCase());
                    notiflist.add(sender);
                    notifs.put(target.toLowerCase(), notiflist);
                    bot.sendMessage(sender, "Notification for " + target + " added");
                } catch (Exception e) {
                    event.respond("Woah! That didn't seem to work properly. Tell JOPH");
                    e.printStackTrace();
                }

            } else {
                try {
                    notiflist.add(sender);
                    notifs.put(target.toLowerCase(), notiflist);
                    bot.sendMessage(sender, "Notification for " + target + " added");
                } catch (Exception e) {
                    event.respond("Woah! That didn't seem to work properly. Tell JOPH");
                    e.printStackTrace();
                }


            }


        } else if (notifs.containsKey(usr.toLowerCase())) {

            String msg = usr + " is now active in " + event.getChannel().getName();

            notiflist = notifs.get(usr.toLowerCase());
            for (String user : notiflist) {
                bot.sendMessage(user, msg);
            }
            notiflist.clear();
            notifs.remove(usr.toLowerCase());


        } else if (line[0].equalsIgnoreCase("!r")) {

            Random rand = new Random();
            String nick = event.getUser().getNick().toString();
            int one;
            int two;
            int three;
            int four;


            one = rand.nextInt(10);
            two = rand.nextInt(10);
            three = rand.nextInt(10);
            four = rand.nextInt(10);
            event.respond("" + one + "" + two + "" + three + "" + four);


            int digit0 = 5;
            int digit1 = 2;
            int digit2 = 2;
            int digit3 = 4;
            int digit4 = 4;
            int digit5 = 6;
            int digit6 = 6;
            int digit7 = 8;
            int digit8 = 5;
            int digit9 = 10;
            if (four == three) {
                if (two == four) {
                    if (one == four) {

                        event.respond("Quads! You get 25 territories");

                    } else {

                        event.respond("Trips! You get 20 territories ");

                    }

                } else {

                    event.respond("Dubs! You get 15 territories");

                }
            } else {
                if (four == 0) {

                    event.respond("0! You get 5 territories ");

                } else if (four == 1) {

                    event.respond("1! You get 2 territories ");

                } else if (four == 2) {

                    event.respond("2! You get 2 territories ");

                } else if (four == 3) {

                    event.respond("3! You get 4 territories ");

                } else if (four == 4) {

                    event.respond("4! You get 4 territories ");

                } else if (four == 5) {

                    event.respond("5! You get 6 territories ");

                } else if (four == 6) {

                    event.respond("6! You get 6 territories ");

                } else if (four == 7) {

                    event.respond("7! You get 8 territories ");

                } else if (four == 8) {

                    event.respond("8! You get 8 territories ");

                } else if (four == 9) {

                    event.respond("9! You get 10 territories ");

                }
            }


        }

    }
}
