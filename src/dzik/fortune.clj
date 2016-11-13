(ns dzik.fortune
  (:require [clojure.java.shell :as sh]))

(defn fortune []
  (try 
    (:out (sh/sh "fortune"))
    (catch Exception e "bad luck")))


