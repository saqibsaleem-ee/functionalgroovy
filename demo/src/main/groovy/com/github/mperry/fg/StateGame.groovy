package com.github.mperry.fg

import fj.F
import fj.P
import fj.P2
import fj.P3
import fj.Unit
import groovy.transform.TypeChecked
import org.junit.Assert
import org.junit.Test

import static fj.P.*
import static org.junit.Assert.*


/**
 * Created by MarkPerry on 14/02/14.
 */
@TypeChecked
class StateGame {

    String increment = "i"
    String decrement = "d"
    String toggle = "t"

    @Test
    void test1() {
        def r = run([P.p("tiitd", true, 0), P.p("tiitd", false, 0)])
        def expected = [-1, 2]
        assertTrue(r == expected)
    }

    List<Integer> run(List<P3<String, Boolean, Integer>> list) {
        list.map { P3<String, Boolean, Integer> p ->
            def s = playGame(p._1())
            def i = s.eval(P.p(p._2(), p._3()))
            i
        }
    }

    StateM<P2<Boolean, Integer>, Unit> command(String s, P2<Boolean, Integer> p) {
        def on = p._1()
        def score = p._2()
        if (on && s == increment) {
            StateM.put(P.p(on, score + 1))
        } else if (on && s == decrement) {
            StateM.put(P.p(on, score - 1))
        } else if (s == toggle) {
            StateM.put(P.p(!on, score))
        } else {
            StateM.put(P.p(on, score))
        }
    }

    StateM<P2<Boolean, Integer>, Integer> playGame(String s) {
        if (s.length() == 0) {
            StateM.<P2<Boolean, Integer>>get().map { P2<Boolean, Integer> p ->
                p._2()
            }
        } else {
            StateM.<P2<Boolean, Integer>>get().flatMap { P2<Boolean, Integer> p ->
                def result = command(s[0], p)
                result.flatMap({ Unit u ->
                    playGame(s.substring(1))
                } as F)
            }
        }
    }

}