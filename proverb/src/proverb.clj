(ns proverb
  (:require [clojure.string :refer [replace-first]]))

(def ^:private sentence-template "For want of a _ the _ was lost.")
(def ^:private ending-template "And all for the want of a _.")

(defn- make-sentence
  ([template word] (replace-first template #"_" word))
  ([template cause effect]
    (reduce #(make-sentence %1 %2) template [cause effect])))

(defn- compose-proverb [words first-cause]
  (->> words
       (partition 2 1)
       (mapv
         #(apply make-sentence sentence-template %))
       (#(conj % (make-sentence ending-template first-cause)))
       (clojure.string/join "\n")))
              
(def proverb
  (compose-proverb
     ["nail" "shoe" "horse" "rider" "message" "battle" "kingdom"]
     "horseshoe nail"))

