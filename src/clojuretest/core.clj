(ns clojuretest.core
  (:gen-class))

(require '[clj-http.client :as http])
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "TESTIONG")
  (def greet (fn [] (println "Hello")))
  (greet)
  (def greet2 #(println "Hello2"))
  (greet2)
  (defn greet3
    ([] (str "Hello, World!"))
    ([x] (str "Hello, " x "!"))
    ([x y] (str x ", " y "!"))
  )
  (println (greet3 "Good morning" "Clojure"))
  ;; For testing
  (assert (= "Hello, World!" (greet3)))
  (assert (= "Hello, Clojure!" (greet3 "Clojure")))
  (assert (= "Good morning, Clojure!" (greet3 "Good morning" "Clojure")))

  (defn do-nothing [x] x)
  (println (do-nothing "THIS"))
  (assert (= "THIS" (do-nothing "THIS")))

  (defn always-thing [& x] :thing)
  (println (always-thing "THIS"))
  (assert (= :thing (always-thing "THIS")))


  (defn make-thingy [x] (fn [& y] x))
  ;; Tests
  (let [n (rand-int Integer/MAX_VALUE)
  f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f :foo)))
  (assert (= n (apply f :foo (range)))))

  (defn triplicate [f] (f) (f) (f))

  (triplicate #(println "TIMES"))

  (defn opposite [f]
    (fn [& args] (not (f))))
  (opposite #([] "what"))

  (defn triplicate2 [f & args]
    (triplicate #(f args)))

  (triplicate2 println "TIMES2")

  (println (Math/cos Math/PI))

  (defn check-proof [x] (+ (Math/pow (Math/sin x) 2) (Math/pow (Math/cos x) 2)))
  (println (check-proof 5))

  (defn http-get [url]
    (slurp url))
  
  (assert (.contains (http-get "http://www.w3.org") "html"))

  (def list ["abc" false 99])
  (println (get list 0))
  (println (count list))

  (def list2 (conj list ["new" "stuff"] "even newer"))
  (println list2)

  (def scores {"fred" 100 "bob" 200})
  (println scores)
  (def scores-updated (assoc scores "sall" 300))
  (println scores-updated)
  (println (scores "fred"))
)

