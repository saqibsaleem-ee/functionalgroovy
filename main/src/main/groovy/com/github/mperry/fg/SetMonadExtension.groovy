package com.github.mperry.fg

import com.github.mperry.fg.typeclass.concrete.ListApplicative
import com.github.mperry.fg.typeclass.concrete.ListMonad
import com.github.mperry.fg.typeclass.concrete.SetMonad
import fj.F
import fj.F2
import fj.F3
import fj.Unit
import groovy.transform.TypeChecked

/**
 * Created by MarkPerry on 13/04/2014.
 */
@TypeChecked
class SetMonadExtension {

    static SetMonad monad() {
        new SetMonad()
    }

    static <A, B> Set<B> ap(Set<A> ma, Set<F<A, B>> mf) {
        monad().ap(ma, mf)
    }

    static <A, B> Set<B> to(Set<A> list, B b) {
        monad().to(list, b)
    }

    static <A> Set<Unit> skip(Set<A> list) {
        monad().skip(list)
    }

    static <A, B> Set<List<B>> replicateM(Set<A> list, Integer n) {
        monad().replicateM(n, list)
    }

    static <A, B> Set<B> liftM(Set<A> ma, F<A, B> f) {
        monad().liftM(ma, f)
    }

    static <A, B, R> Set<R> liftM2(Set<A> ma, Set<B> mb, F2<A, B, R> f) {
        monad().liftM2(ma, mb, f)
    }

    static <A, B, C, R> Set<R> liftM3(Set<A> ma, Set<B> mb, Set<C> mc, F3<A, B, C, R> f) {
        monad().liftM3(ma, mb, mc, f)
    }

    static <A, B, C> Set<C> map2(Set<A> listA, Set<B> listB, F2<A, B, C> f) {
        monad().map2(listA, listB, f)
    }

    // Applicative

    static <A, B> Set<B> apply(Set<A> list, Set<F<A, B>> listFs) {
        monad().apply(listFs, list)
    }

    static <A, B> Set<B> liftA(Set<A> a1, F<A, B> f) {
        monad().liftA(f, a1)
    }

    static <A, B, C> Set<C> liftA2(Set<A> listAs, Set<B> listBs, F2<A, B, C> f) {
        monad().liftA2(f, listAs, listBs)
    }

    static <A, B, C, D> Set<D> liftA3(Set<A> apa, Set<B> apb, Set<C> apc, F3<A, B, C, D> f) {
        monad().liftA3(f, apa, apb, apc)
    }

}
