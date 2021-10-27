package demo.commands;

import net.minestom.server.command.builder.Command;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Player;
import net.minestom.server.utils.entity.EntityFinder;

import java.util.List;

import static net.minestom.server.command.builder.arguments.ArgumentType.Boolean;
import static net.minestom.server.command.builder.arguments.ArgumentType.*;

public class AutoViewCommand extends Command {
    public AutoViewCommand() {
        super("autoview");

        // Modify viewable
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            final boolean autoView = context.get("auto-view");
            player.setAutoViewable(autoView);
            player.sendMessage("Auto-viewable set to " + autoView);
        }, Literal("set-auto-viewable"), Boolean("auto-view"));

        // Modify viewer
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            final boolean autoView = context.get("auto-view");
            player.setAutoViewer(autoView);
            player.sendMessage("Auto-viewer set to " + autoView);
        }, Literal("set-auto-viewer"), Boolean("auto-view"));

        // Modify viewable rule
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            EntityFinder finder = context.get("targets");
            final List<Entity> entities = finder.find(sender);
            player.updateViewingRule(entities::contains);
            player.sendMessage("Viewable rule updated to see " + entities.size() + " players");
        }, Literal("rule-viewable"), Entity("targets").onlyPlayers(true));

        // Modify viewer rule
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            EntityFinder finder = context.get("targets");
            final List<Entity> entities = finder.find(sender);
            player.updateViewerRule(entities::contains);
            player.sendMessage("Viewer rule updated to see " + entities.size() + " entities");
        }, Literal("rule-viewer"), Entity("targets"));

        // Remove viewable rule
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            player.updateViewingRule(p -> true);
            player.sendMessage("Viewable rule removed");
        }, Literal("remove-rule-viewable"));

        // Remove viewer rule
        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            player.updateViewerRule(p -> true);
            player.sendMessage("Viewer rule removed");
        }, Literal("remove-rule-viewer"));
    }
}
