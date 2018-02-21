(defproject dzik "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-codec "1.0.1"]
                 [clj-http "2.3.0"]
                 [org.clojure/data.json "0.2.6"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler dzik.core/giphy-handler})
