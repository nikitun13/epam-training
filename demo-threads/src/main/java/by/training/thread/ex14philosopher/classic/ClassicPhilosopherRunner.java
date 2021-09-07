package by.training.thread.ex14philosopher.classic;

public class ClassicPhilosopherRunner {

    public static void main(String[] args) {
        Fork firstFork = new Fork();
        Fork secondFork = new Fork();
        Fork thirdFork = new Fork();
        Fork fourthFork = new Fork();
        Fork fifthFork = new Fork();

        Philosopher firstPhilosopher = new Philosopher("Сократ", firstFork, secondFork);
        Philosopher secondPhilosopher = new Philosopher("Платон", secondFork, thirdFork);
        Philosopher thirdPhilosopher = new Philosopher("Аристотель", thirdFork, fourthFork);
        Philosopher fourthPhilosopher = new Philosopher("Кант", fourthFork, fifthFork);
        Philosopher fifthPhilosopher = new Philosopher("Декарт", fifthFork, firstFork);

        firstPhilosopher.start();
        secondPhilosopher.start();
        thirdPhilosopher.start();
        fourthPhilosopher.start();
        fifthPhilosopher.start();
    }
}
