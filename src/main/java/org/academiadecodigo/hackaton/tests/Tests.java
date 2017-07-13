package org.academiadecodigo.hackaton.tests;

import org.academiadecodigo.hackaton.client.Session;
import org.academiadecodigo.hackaton.shared.Message;
import org.academiadecodigo.hackaton.shared.Type;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by André Martins <Code Cadet>
 *         Project stalkers (13/07/17)
 *         <Academia de Código_>
 */
public class Tests {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String msg;
        while (!(msg = scanner.nextLine()).equals("exit")) {

            System.out.println(msg);

            Pattern pattern = Pattern.compile("[\\w]+");
            Matcher matcher = pattern.matcher(msg);

            if (matcher.matches()) {
                System.out.println("ok");
            }

        }

    }

}
