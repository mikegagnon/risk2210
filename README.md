Risk2210
========

Simulates battles for Risk 2210 AD via Scala repl.

I work at Twitter on the *Revenue Risk* team. To celebrate our work we decided to play
a game of Risk 2210 AD. But it would set a bad example for us to play such a risky
game without having a mechanism to calculate our risks.

Hence this simulator.

Now we can play *Calculated Risk 2210 A.D.*

Sample usage
------------
```
> sbt console
```

```scala
scala> import com.mikegagnon.risk.Risk._

// Roll three six-sided dice (for the attacker) against two 8-sided dice (for the defender)
scala> roll(6,6,6)(8,8)
Attacker rolls: ArrayBuffer(6, 4, 3)
Defender rolls: ArrayBuffer(6, 2)
Attacker wins 1
Defender wins 1

res7: (Int, Int) = (1,1)
```

```scala
// Same as a above, but do it many times and report the average.
// The average number of attacker wins is 0.768 (and 1.232 for the defender)
scala> rollTrials(6,6,6)(8,8)()
res9: (Double, Double) = (0.768,1.232)
```

For more usage examples, and source, see `src/main/scala/com/mikegagnon/risk/risk.scala`
