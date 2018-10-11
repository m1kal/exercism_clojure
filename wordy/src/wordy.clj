(ns wordy)

(defn- parse-op [[_ op value]]
  [(clojure.string/trim op) (read-string (or value "0"))])

(defn- parse [input]
  (->> input
       (re-seq #"([^-\d]*)(-?\d+)?")
       butlast
       (map parse-op)))

(def ^:private operations
  {"plus" +
   "minus" -
   "multiplied by" *
   "divided by" /
   "What is" (fn [_ x] x)
   "?" (fn [x _] x)})

(defn- get-op [op]
  (or (operations op) (throw (IllegalArgumentException.))))

(defn- execute [arg1 [op arg2]]
  ((get-op op) arg1 arg2))

(defn evaluate [input]
  (reduce execute nil (parse input)))

