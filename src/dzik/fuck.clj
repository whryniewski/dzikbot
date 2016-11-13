(ns dzik.fuck
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(defn awesome [someone]
  (let [response (client/get (format "http://foaas.com/awesome/%s" someone) {:accept :json})]
    (get (json/read-str (:body response)) "message")))

(defn fuck-off [name from]
  (let [response (client/get (format "http://foaas.com/off/%s/%s" name from) {:accept :json})
        parsed (json/read-str (:body response))]
    (format "%s %s" (get parsed "message") (get parsed "subtitle"))))


