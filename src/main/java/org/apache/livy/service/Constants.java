package org.apache.livy.service;

public class Constants {

  public static final String SECRET = "0ffffffd50ffffffb10ffffffc7270ffffffb60ffffffd10ffffff8d0ffffff980ffffffae4f0ffffffd2250ffffffa3757e0ffffff890ffffffc80ffffffd80ffffffab0ffffffdf0166d0ffffffb80ffffffa2224264160ffffffe96d29";

  public static final String CODE_1 = "\ny = 10\nx = 6 + 3\nprint(x**2+y)\n\n";

  public static final String CODE_2 = "import random\n"
      + "NUM_SAMPLES = 100000\n"
      + "\n"
      + "def sample(p):\n"
      + "      x, y = random.random(), random.random()\n"
      + "      return 1 if x*x + y*y < 1 else 0\n"
      + "\n"
      + "count = sc.parallelize(xrange(0, NUM_SAMPLES)).map(sample).reduce(lambda a, b: a + b)\n"
      + "\n"
      + "print \"Pi is roughly %f \" % (4.0 * count / NUM_SAMPLES)";

}
