/*
 * Copyright (c) 2014 Andrey Gavrikov.
 * this file is part of tooling-force.com application
 * https://github.com/neowit/tooling-force.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.neowit.utils

import scala.util.parsing.json.{JSON, JSONObject}

/**
 * this is not TRUE JSON properties
 * this class allows values in two formats
 * - simple string
 * - JSON string
 */
trait JsonProperties {
    val jsonProps: PropertiesOption

    type Data = Map[String, String]
    /**
     * convert key/value pairs into JSON and save in session file
     * @param data - map of values to save
     */
    def setJsonData(key: String, data: Data) {
        jsonProps.setProperty(key, JSONObject(data).toString())
    }
    /**
     * retrieve key/value pairs for given object name from session file
     * @return - map of values restored from session file
     */
    def getJsonData(key: String): Data = {
        jsonProps.getPropertyOption(key) match {
            case Some(x) => valueToDataMap(x)
            case None => Map()
        }
    }

    /**
     * getKeyByValue is the opposite of getJsonData, i.e. returns first key which contains specified data in the specified field
     */
    def getKeyByValue(fName: String, fVal: String):Option[String] = {
        //find key of the element which contains given Id
        jsonProps.keySet().toArray.find(key => Some(fVal) == getJsonData(key.asInstanceOf[String]).get(fName)) match {
            case Some(x) => Option(x.asInstanceOf[String])
            case None => None
        }
    }
    private def valueToDataMap(s: String):Data = {
        JSON.parseFull(s) match {
            case Some(m)  => m.asInstanceOf[Data]
            case _ => Map()
        }
    }

    def getKeyById(id: String):Option[String] = {
        getKeyByValue("Id", id)
    }
    def setField(key: String, propName:String, value: String) {
        val x = getJsonData(key)
        setJsonData(key, x ++ Map(propName -> value))
    }

    /**
     * remove specified field from data map
     * @param propName - name of the field to be removed
     */
    def clearField(key: String, propName:String) {
        val data = getJsonData(key)
        setJsonData(key, data - "Id")
    }
    def remove(key: String) {
        jsonProps.remove(key)
    }

    /**
     * @return field value in JSON format
     */
    def getField(key: String, propName: String): Option[String] = {
        getJsonData(key).get(propName)
    }

}
