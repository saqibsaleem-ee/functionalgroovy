package com.github.mperry.fg.typeclass.concrete

import com.github.mperry.fg.typeclass.Applicative
import fj.F
import fj.P2
import groovy.transform.TypeChecked

/**
 * Created by MarkPerry on 10/04/2014.
 */
@TypeChecked
class ListApplicative extends Applicative<List> {

    @Override
    def <A> List<A> pure(A a) {
        [a]
    }

    @Override
    def <A, B> List<B> apply(List<F<A, B>> fs, List<A> list) {
        fs.flatMap { F<A, B> f ->
            list.map { A a ->
                f.f(a)
            }
        }
    }

    @Override
    def <A, B> List<B> map(List<A> list, F<A, B> f) {
        list.map(f)
    }

}
