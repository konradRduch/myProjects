/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pl.polsl.view;

import java.util.List;

/**
 * An interface for classes that display content.
 *
 * @param <T> The type of content to be displayed.
 *
 * @author Konrad Rduch
 * @version f2
 * @since p1
 */
public interface ContentDisplayView<T> {

    /**
     * Displays the provided list of content.
     *
     * @param contentList The list of content to be displayed.
     */
    void showContent(List<T> contentList);
}
